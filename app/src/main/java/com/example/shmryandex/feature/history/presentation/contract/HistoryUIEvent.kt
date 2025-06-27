package com.example.shmryandex.feature.history.presentation.contract

import com.example.shmryandex.core.presentation.mvi.UIEvent

sealed class HistoryUIEvent: UIEvent {
    data class StartDateSelected(
        val startDate: String
    ): HistoryUIEvent()

    data class EndDateSelected(
        val endDate: String
    ): HistoryUIEvent()
}