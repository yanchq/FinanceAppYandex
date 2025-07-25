package com.example.core.domain.entity

data class Transaction(
    val id: Long,
    val amount: Double,
    val categoryId: Int,
    val accountId: Int,
    val comment: String,
    val transactionDate: String, // 2025-06-01
    val transactionTime: String
)