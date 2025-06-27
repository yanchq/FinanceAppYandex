package com.example.shmryandex.feature.history.domain.entity

/**
 * Доменная модель элемента истории транзакций.
 * Содержит информацию о транзакции для отображения в истории: сумму, валюту, комментарий и время создания.
 */
data class HistoryItem(
    val id: Long,
    val name: String,
    val emoji: String,
    val amount: Double,
    val currency: String,
    val comment: String = "",
    val createdAt: String
)
