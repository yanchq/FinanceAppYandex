package com.example.shmryandex.presentation.screens.expenses.history

import com.example.shmryandex.domain.entity.Expense
import java.time.LocalDate

data class ExpensesHistoryUiState(
    val startDate: LocalDate = LocalDate.now(),
    val endDate: LocalDate = LocalDate.now(),
    val expenses: List<Expense> = emptyList(),
    val totalAmount: Double = 0.0
)
