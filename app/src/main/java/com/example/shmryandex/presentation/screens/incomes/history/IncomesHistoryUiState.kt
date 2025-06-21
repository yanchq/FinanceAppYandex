package com.example.shmryandex.presentation.screens.incomes.history

import com.example.shmryandex.domain.entity.Income
import java.time.LocalDate

data class IncomesHistoryUiState(
    val startDate: LocalDate = LocalDate.now(),
    val endDate: LocalDate = LocalDate.now(),
    val incomes: List<Income> = emptyList(),
    val totalAmount: Double = 0.0
)
