package com.example.core.data.network.model

data class CreateTransactionRequestBody(
    val accountId: Int,
    val categoryId: Int,
    val amount: String,
    val transactionDate: String,
    val comment: String
)