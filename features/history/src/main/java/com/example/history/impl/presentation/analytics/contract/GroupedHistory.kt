package com.example.history.impl.presentation.analytics.contract

data class GroupedHistory(
    val name: String,
    val amount: Double,
    val emoji: String,
    val percentage: Double
)
