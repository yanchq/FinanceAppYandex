package com.example.shmryandex.feature.expenses.presentation.contract

import com.example.shmryandex.core.presentation.mvi.UIState
import com.example.shmryandex.feature.expenses.domain.entity.Expense

data class ExpensesUIState(
    val expenses: List<Expense> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
): UIState {
    val summary: Double
        get() = expenses.sumOf { it.amount }
}
