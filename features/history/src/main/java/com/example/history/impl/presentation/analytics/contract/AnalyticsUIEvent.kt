package com.example.history.impl.presentation.analytics.contract

import com.example.core.presentation.mvi.UIEvent
import com.example.history.impl.presentation.main.contract.HistoryUIEvent

sealed interface AnalyticsUIEvent: UIEvent {

    /**
     * Событие выбора начальной даты периода.
     * @property startDate Выбранная начальная дата в формате YYYY-MM-DD
     */
    data class StartDateSelected(
        val startDate: String,
        val isIncome: Boolean
    ): AnalyticsUIEvent

    /**
     * Событие выбора конечной даты периода.
     * @property endDate Выбранная конечная дата в формате YYYY-MM-DD
     */
    data class EndDateSelected(
        val endDate: String,
        val isIncome: Boolean
    ): AnalyticsUIEvent
}