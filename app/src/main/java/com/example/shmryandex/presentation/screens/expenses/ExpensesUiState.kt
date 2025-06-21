package com.example.shmryandex.presentation.screens.expenses

import com.example.shmryandex.domain.entity.Expense

data class ExpensesUiState(
    val expenses: List<Expense> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
) {
    val summary: Double
        get() = expenses.sumOf { it.amount }
}