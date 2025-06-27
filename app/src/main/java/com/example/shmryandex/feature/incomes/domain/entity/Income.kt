package com.example.shmryandex.feature.incomes.domain.entity

data class Income(
    val id: Long,
    val name: String,
    val emoji: String,
    val amount: Double,
    val currency: String,
    val comment: String = "",
    val createdAt: String
) {
}