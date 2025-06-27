package com.example.shmryandex.feature.history.domain.entity

data class HistoryItem(
    val id: Long,
    val name: String,
    val emoji: String,
    val amount: Double,
    val currency: String,
    val comment: String = "",
    val createdAt: String
)
