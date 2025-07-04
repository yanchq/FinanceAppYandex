package com.example.shmryandex.feature.expenses.presentation.contract

import com.example.shmryandex.core.presentation.mvi.UIEvent

/**
 * Sealed класс для определения событий UI экрана расходов.
 * В текущей реализации не содержит событий, но подготовлен для
 * будущего расширения функциональности.
 */
sealed class ExpensesUIEvent: UIEvent {

    data object LoadExpenses : ExpensesUIEvent()
}