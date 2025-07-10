package com.example.expenses.impl.presentation.contract

import com.example.core.presentation.mvi.UIState
import com.example.expenses.impl.domain.entity.Expense

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
