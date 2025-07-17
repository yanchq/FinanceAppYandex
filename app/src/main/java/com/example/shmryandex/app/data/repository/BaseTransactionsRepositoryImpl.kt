package com.example.shmryandex.app.data.repository

import com.example.shmryandex.app.data.mapper.TransactionMapper
import com.example.core.data.network.api.TransactionsApi
import com.example.core.data.network.model.CreateTransactionRequestBody
import com.example.core.data.network.model.Result
import com.example.core.data.storage.dao.TransactionsDao
import com.example.core.data.storage.entity.TransactionDbModel
import com.example.core.domain.entity.DetailedTransaction
import com.example.core.domain.entity.Transaction
import com.example.core.domain.repository.BaseTransactionsRepository
import com.example.core.domain.repository.NetworkRepository
import com.example.core.utils.extractDate
import com.example.core.utils.extractTime
import com.example.core.utils.toUtcIsoString
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
        }
        else {
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
            } else {
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
            }
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