package com.example.core.data.repository

import com.example.core.data.mapper.TransactionMapper
import com.example.core.data.network.api.TransactionsApi
import com.example.core.data.network.model.CreateTransactionRequestBody
import com.example.core.data.network.model.Result
import com.example.core.domain.entity.DetailedTransaction
import com.example.core.domain.repository.BaseTransactionsRepository
import javax.inject.Inject


class BaseTransactionsRepositoryImpl @Inject constructor(
    private val api: TransactionsApi,
    private val mapper: TransactionMapper
): BaseTransactionsRepository {
    override suspend fun createTransaction(
        accountId: Int,
        categoryId: Int,
        amount: String,
        transactionDate: String,
        comment: String
    ): Result<Unit> = Result.execute {
        api.createTransaction(
            CreateTransactionRequestBody(
                accountId = accountId,
                categoryId = categoryId,
                amount = amount,
                transactionDate = transactionDate,
                comment = comment
            )
        )
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
            val transactionDto = api.getTransactionById(transactionId)
            val transaction = mapper.mapTransactionDtoToDetailedTransaction(transactionDto)
            transaction
        }
}