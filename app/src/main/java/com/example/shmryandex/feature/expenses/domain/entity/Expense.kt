package com.example.shmryandex.feature.expenses.domain.entity

data class Expense(
    val id: Long,
    val name: String,
    val emoji: String,
    val amount: Double,
    val currency: String,
    val comment: String = "",
    val createdAt: String
) {
}