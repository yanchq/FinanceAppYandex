package com.example.shmryandex.feature.history.presentation.contract

import com.example.shmryandex.core.presentation.mvi.UIEvent

/**
 * Sealed класс, представляющий события пользовательского интерфейса для экрана истории транзакций.
 * Определяет все возможные действия пользователя на экране, такие как выбор дат периода.
 */
sealed class HistoryUIEvent: UIEvent {
    
    /**
     * Событие выбора начальной даты периода.
     * @property startDate Выбранная начальная дата в формате YYYY-MM-DD
     */
    data class StartDateSelected(
        val startDate: String,
        val isIncome: Boolean
    ): HistoryUIEvent()

    /**
     * Событие выбора конечной даты периода.
     * @property endDate Выбранная конечная дата в формате YYYY-MM-DD
     */
    data class EndDateSelected(
        val endDate: String,
        val isIncome: Boolean
    ): HistoryUIEvent()
}