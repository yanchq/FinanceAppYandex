package com.example.shmryandex.presentation.screens.expenses

import com.example.shmryandex.domain.entity.Expense

data class ExpensesUiState(
    val expenses: List<Expense> = emptyList(),
    val summary: Int = 0
) {
}