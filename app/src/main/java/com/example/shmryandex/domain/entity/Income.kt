package com.example.shmryandex.domain.entity

data class Income(
    val id: Long,
    val category: Category,
    val amount: Double,
    val comment: String = "",
    val createdAt: String
) {
}