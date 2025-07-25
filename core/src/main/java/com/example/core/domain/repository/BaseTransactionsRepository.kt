package com.example.core.domain.repository

import com.example.core.data.network.model.Result
import com.example.core.domain.entity.Account
import com.example.core.domain.entity.DetailedTransaction
import com.example.core.domain.entity.Transaction

interface BaseTransactionsRepository {

    suspend fun createTransaction(
       accountId: Int,
       categoryId: Int,
       amount: String,
       transactionDate: String,
       comment: String
    ): Result<Transaction>

    suspend fun editTransaction(
        transactionId: Int,
        accountId: Int,
        categoryId: Int,
        amount: String,
        transactionDate: String,
        comment: String
    ): Result<Unit>

    suspend fun getTransactionById(
        transactionId: Int
    ): Result<DetailedTransaction>

    suspend fun syncTransactions(): Result<Unit>

    suspend fun getTransactionsByPeriod(
        accounts: List<Account>,
        startDate: String,
        endDate: String
    ): Result<List<Transaction>>
}