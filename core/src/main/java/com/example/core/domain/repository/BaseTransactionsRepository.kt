package com.example.core.domain.repository

import com.example.core.data.network.model.Result

interface BaseTransactionsRepository {

    suspend fun createTransaction(
       accountId: Int,
       categoryId: Int,
       amount: String,
       transactionDate: String,
       comment: String
    ): Result<Unit>

    suspend fun editTransaction(
        transactionId: Int,
        accountId: Int,
        categoryId: Int,
        amount: String,
        transactionDate: String,
        comment: String
    ): Result<Unit>
}