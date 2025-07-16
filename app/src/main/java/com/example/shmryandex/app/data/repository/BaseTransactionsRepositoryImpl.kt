package com.example.shmryandex.app.data.repository

import android.util.Log
import com.example.shmryandex.app.data.mapper.TransactionMapper
import com.example.core.data.network.api.TransactionsApi
import com.example.core.data.network.model.CreateTransactionRequestBody
import com.example.core.data.network.model.CreateTransactionResponse
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
        Log.d("SyncStatus", "Pending transactions: $pendingTransactionsDb")

        pendingTransactionsDb.forEach { transaction ->
            if (transaction.localId != null) {
                when (val response = createTransaction(
                    accountId = transaction.accountId,
                    categoryId = transaction.categoryId,
                    amount = transaction.amount.toString(),
                    transactionDate = toUtcIsoString(
                        transaction.transactionDate,
                        transaction.transactionTime
                    ),
                    comment = transaction.comment
                )) {
                    is Result.Error -> {
                        Log.d("SyncStatus", response.exception.toString())
                    }
                    Result.Loading -> {}
                    is Result.Success<Transaction> -> {
                        try {
                            Log.d("SyncStatus", "Pending Transaction id: ${transaction.id}")
                            transactionsDao.deleteTransactionById(transaction.id)
                            transactionsDao.insertTransaction(
                                mapper.mapTransactionToDbModel(
                                    response.data
                                )
                            )
                        } catch (e: Exception) {
                            Log.d("SyncStatus", e.message.toString())
                        }
                    }
                }
            } else {
                when (editTransaction(
                    transactionId = transaction.id,
                    accountId = transaction.accountId,
                    categoryId = transaction.categoryId,
                    amount = transaction.amount.toString(),
                    transactionDate = toUtcIsoString(
                        transaction.transactionDate,
                        transaction.transactionTime
                    ),
                    comment = transaction.comment
                )) {
                    is Result.Error -> {}
                    Result.Loading -> {}
                    is Result.Success<*> -> {
                        transactionsDao.changeTransactionSyncStatus(
                            transactionId = transaction.id
                        )
                    }
                }
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