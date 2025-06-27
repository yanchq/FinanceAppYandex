package com.example.shmryandex.feature.expenses.presentation.contract

import com.example.shmryandex.core.presentation.mvi.UIState
import com.example.shmryandex.feature.expenses.domain.entity.Expense

/**
 * Класс состояния UI экрана расходов.
 * Содержит список расходов, флаги загрузки и ошибки,
 * а также вычисляемое свойство для получения общей суммы расходов.
 */
data class ExpensesUIState(
    val expenses: List<Expense> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
): UIState {
    val summary: Double
        get() = expenses.sumOf { it.amount }
}
