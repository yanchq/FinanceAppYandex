package com.example.core.domain.entity

data class DetailedTransaction(
    val id: Long,
    val name: String,
    val amount: Double,
    val category: Category,
    val account: Account,
    val comment: String,
    val transactionDate: String,
    val transactionTime: String
)
