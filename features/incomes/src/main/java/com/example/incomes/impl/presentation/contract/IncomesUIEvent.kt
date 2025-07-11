package com.example.incomes.impl.presentation.contract

import com.example.core.presentation.mvi.UIEvent

/**
 * Sealed класс, определяющий события пользовательского интерфейса для экрана доходов.
 * Представляет все возможные действия пользователя на экране.
 */
sealed interface IncomesUIEvent: UIEvent {

    data object LoadIncomes: IncomesUIEvent
}