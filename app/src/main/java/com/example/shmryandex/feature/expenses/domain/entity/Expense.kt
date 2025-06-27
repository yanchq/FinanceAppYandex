package com.example.shmryandex.feature.expenses.domain.entity

/**
 * Доменная модель расхода.
 * Представляет расходную операцию с информацией о категории (название и эмодзи),
 * сумме, валюте, комментарии и времени создания.
 */
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