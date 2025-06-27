package com.example.shmryandex.feature.incomes.domain.entity

/**
 * Доменная модель дохода.
 * Содержит информацию о транзакции типа "доход": сумму, валюту, комментарий и время создания.
 */
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