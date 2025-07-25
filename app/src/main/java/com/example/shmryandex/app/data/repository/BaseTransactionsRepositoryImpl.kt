package com.example.shmryandex.app.data.repository

import com.example.shmryandex.app.data.mapper.TransactionMapper
import com.example.core.data.network.api.TransactionsApi
import com.example.core.data.network.model.CreateTransactionRequestBody
import com.example.core.data.network.model.Result
import com.example.core.data.storage.dao.TransactionsDao
import com.example.core.data.storage.entity.TransactionDbModel
import com.example.core.domain.entity.Account
import com.example.core.domain.entity.DetailedTransaction
import com.example.core.domain.entity.Transaction
import com.example.core.domain.repository.BaseTransactionsRepository
import com.example.core.domain.repository.NetworkRepository
import com.example.core.utils.extractDate
import com.example.core.utils.extractTime
import com.example.core.utils.toUtcIsoString
import com.example.history.impl.domain.entity.HistoryItem
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import java.text.SimpleDateFormat
import java.time.Instant
import java.util.Date
import java.util.Locale
import javax.inject.Inject


class BaseTransactionsRepositoryImpl @Inject constructor(
    private val api: TransactionsApi,
    private val mapper: TransactionMapper,
    private val networkRepository: NetworkRepository,
    private val transactionsDao: TransactionsDao
) : BaseTransactionsRepository {

    override suspend fun createTransaction(
        accountId: Int,
        categoryId: Int,
        amount: String,
        transactionDate: String,
        comment: String
    ): Result<Transaction> = Result.execute {
        if (networkRepository.getNetworkStatus().value) {
            createNetworkTransaction(
                accountId = accountId,
                categoryId = categoryId,
                amount = amount,
                transactionDate = transactionDate,
                comment = comment
            )
        } else {
            createLocalTransaction(
                accountId = accountId,
                categoryId = categoryId,
                amount = amount,
                transactionDate = transactionDate,
                comment = comment
            )
        }
    }

    private suspend fun createLocalTransaction(
        accountId: Int,
        categoryId: Int,
        amount: String,
        transactionDate: String,
        comment: String
    ): Transaction {
        val currentTime = -System.currentTimeMillis().toInt()
        val transactionDb = TransactionDbModel(
            id = currentTime,
            amount = amount.toDouble(),
            comment = comment,
            transactionDate = transactionDate.extractDate(),
            transactionTime = transactionDate.extractTime(),
            accountId = accountId,
            categoryId = categoryId,
            localId = currentTime.toString(),
            syncStatus = "pending"
        )
        transactionsDao.insertTransaction(transactionDb)
        return mapper.mapTransactionDbToTransaction(transactionDb)
    }

    private suspend fun createNetworkTransaction(
        accountId: Int,
        categoryId: Int,
        amount: String,
        transactionDate: String,
        comment: String
    ): Transaction {
        val response = api.createTransaction(
            CreateTransactionRequestBody(
                accountId = accountId,
                categoryId = categoryId,
                amount = amount,
                transactionDate = transactionDate,
                comment = comment
            )
        )
        transactionsDao.insertTransaction(mapper.mapTransactionResponseToDbModel(response))
        return mapper.mapTransactionResponseToTransaction(response)
    }

    override suspend fun editTransaction(
        transactionId: Int,
        accountId: Int,
        categoryId: Int,
        amount: String,
        transactionDate: String,
        comment: String
    ): Result<Unit> = Result.execute {
        if (networkRepository.getNetworkStatus().value) {
            editNetworkTransaction(
                transactionId = transactionId,
                accountId = accountId,
                categoryId = categoryId,
                amount = amount,
                transactionDate = transactionDate,
                comment = comment
            )
        } else {
            editLocalTransaction(
                transactionId = transactionId,
                accountId = accountId,
                categoryId = categoryId,
                amount = amount,
                transactionDate = transactionDate,
                comment = comment
            )
        }
    }

    private suspend fun editNetworkTransaction(
        transactionId: Int,
        accountId: Int,
        categoryId: Int,
        amount: String,
        transactionDate: String,
        comment: String
    ) {
        api.editTransaction(
            transactionId = transactionId,
            CreateTransactionRequestBody(
                accountId = accountId,
                categoryId = categoryId,
                amount = amount,
                transactionDate = transactionDate,
                comment = comment
            )
        )
        val editedTransactionDb = TransactionDbModel(
            id = transactionId,
            amount = amount.toDouble(),
            categoryId = categoryId,
            accountId = accountId,
            transactionDate = transactionDate.extractDate(),
            transactionTime = transactionDate.extractTime(),
            comment = comment
        )

        transactionsDao.editTransaction(editedTransactionDb)
    }

    private suspend fun editLocalTransaction(
        transactionId: Int,
        accountId: Int,
        categoryId: Int,
        amount: String,
        transactionDate: String,
        comment: String
    ) {
        val transactionDb = transactionsDao.getTransactionById(transactionId)
        val editedTransactionDb = transactionDb.transaction.copy(
            id = transactionId,
            amount = amount.toDouble(),
            categoryId = categoryId,
            accountId = accountId,
            transactionDate = transactionDate.extractDate(),
            transactionTime = transactionDate.extractTime(),
            comment = comment,
            updatedAt = System.currentTimeMillis(),
            syncStatus = "pending"
        )

        transactionsDao.editTransaction(editedTransactionDb)
    }

    override suspend fun getTransactionById(transactionId: Int): Result<DetailedTransaction> =
        Result.execute {
            if (networkRepository.getNetworkStatus().value) {
                getTransactionFromNetwork(transactionId)
            } else {
                getTransactionFromDb(transactionId)
            }
        }

    override suspend fun syncTransactions(): Result<Unit> = Result.execute {
        val pendingTransactionsDb = transactionsDao.getPendingTransactions()

        pendingTransactionsDb.forEach { transaction ->
            if (transaction.localId != null) {
                syncCreatedTransaction(transaction)
            } else {
                syncEditedTransaction(transaction)
            }
        }
    }

    override suspend fun getTransactionsByPeriod(
        accounts: List<Account>,
        startDate: String,
        endDate: String
    ): Result<List<Transaction>> = Result.execute {

        if (networkRepository.getNetworkStatus().value) {
            loadTransactionsFromNetwork(
                accounts = accounts,
                startDate = startDate,
                endDate = endDate,
            )
        }
        else {
            loadTransactionsFromDb(
                startDate = startDate,
                endDate = endDate,
            )
        }
    }

    private suspend fun loadTransactionsFromNetwork(
        accounts: List<Account>,
        startDate: String,
        endDate: String,
    ): List<Transaction> {
        val transactionsDto = coroutineScope {
            accounts.map { account ->
                async {
                    api.getTransactionsByAccountPeriod(
                        accountId = account.id,
                        startDate = startDate,
                        endDate = endDate
                    )
                }
            }
        }.awaitAll().flatten()
            .sortedByDescending { it.createdAt }


        val transactionDomain = transactionsDto
            .map { dto ->
                mapper.mapTransactionDtoToTransaction(dto)
                    .copy(
                        amount = if (dto.category.isIncome) {
                            dto.amount.toDouble()
                        }
                        else {
                            -dto.amount.toDouble()
                        }
                    )
            }

        val transactionDb = transactionsDto
            .map { dto ->
                mapper.mapTransactionDtoToDbModel(dto)
            }

        transactionsDao.insertTransactionsList(transactionDb)

        return transactionDomain
    }

    private suspend fun loadTransactionsFromDb(
        startDate: String,
        endDate: String,
    ): List<Transaction> {
        return transactionsDao.getTransactionsByPeriod(
            startDate = startDate,
            endDate = endDate
        ).map { db ->
            mapper.mapTransactionWithRelationsDbToTransaction(db)
                .copy(
                    amount = if (db.category.isIncome) {
                        db.transaction.amount
                    }
                    else {
                        -db.transaction.amount
                    }
                )
        }
    }

    private suspend fun syncCreatedTransaction(transaction: TransactionDbModel) {
        val response = createNetworkTransaction(
            accountId = transaction.accountId,
            categoryId = transaction.categoryId,
            amount = transaction.amount.toString(),
            transactionDate = toUtcIsoString(
                transaction.transactionDate,
                transaction.transactionTime
            ),
            comment = transaction.comment
        )
        transactionsDao.deleteTransactionById(transaction.id)
        transactionsDao.insertTransaction(
            mapper.mapTransactionToDbModel(
                response
            )
        )
    }

    private suspend fun syncEditedTransaction(transaction: TransactionDbModel) {
        val networkTransaction = api.getTransactionById(transaction.id)

        val networkTransactionUpdateTime =
            Instant.parse(networkTransaction.updatedAt).toEpochMilli()

        if (networkTransactionUpdateTime <= transaction.updatedAt) {
            editNetworkTransaction(
                transactionId = transaction.id,
                accountId = transaction.accountId,
                categoryId = transaction.categoryId,
                amount = transaction.amount.toString(),
                transactionDate = toUtcIsoString(
                    transaction.transactionDate,
                    transaction.transactionTime
                ),
                comment = transaction.comment
            )
            transactionsDao.changeTransactionSyncStatus(
                transactionId = transaction.id
            )
        } else {
            transactionsDao.insertTransaction(mapper.mapTransactionDtoToDbModel(networkTransaction))
        }
    }

    private suspend fun getTransactionFromNetwork(transactionId: Int): DetailedTransaction {
        val transactionDto = api.getTransactionById(transactionId)
        val transaction = mapper.mapTransactionDtoToDetailedTransaction(transactionDto)
        return transaction
    }

    private suspend fun getTransactionFromDb(transactionId: Int): DetailedTransaction {
        val transactionDb = transactionsDao.getTransactionById(transactionId)

        return mapper.mapTransactionDbToDetailedTransaction(transactionDb)
    }
}