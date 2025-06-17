package com.example.shmryandex.domain.entity

data class Expense(
    val id: Long,
    val category: Category,
    val amount: Double,
    val comment: String = "",
    val createdAt: String
) {
}