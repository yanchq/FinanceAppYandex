package com.example.shmryandex.feature.history.presentation.contract

import com.example.shmryandex.core.presentation.mvi.UIState
import com.example.shmryandex.feature.history.domain.entity.HistoryItem
import java.time.LocalDate

data class HistoryUIState(
    val startDate: String = LocalDate.now().withDayOfMonth(1).toString(),
    val endDate: String = LocalDate.now().toString(),
    val items: List<HistoryItem> = emptyList(),

): UIState {
    val totalAmount = items.sumOf { it.amount }
}