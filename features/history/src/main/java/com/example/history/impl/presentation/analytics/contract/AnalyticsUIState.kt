package com.example.history.impl.presentation.analytics.contract

import com.example.core.presentation.mvi.ScreenContent
import com.example.core.presentation.mvi.UIState
import java.time.LocalDate

data class AnalyticsUIState(
    val startDate: String = LocalDate.now().withDayOfMonth(1).toString(),
    val endDate: String = LocalDate.now().toString(),
    val items: List<GroupedHistory> = emptyList(),
    val screenContent: ScreenContent = ScreenContent.Loading
): UIState {
    val totalAmount = items.sumOf { it.amount }
}