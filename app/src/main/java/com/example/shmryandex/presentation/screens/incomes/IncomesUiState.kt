package com.example.shmryandex.presentation.screens.incomes

import com.example.shmryandex.domain.entity.Income

data class IncomesUiState(
    val incomes: List<Income> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
) {
    val summary: Double
        get() = incomes.sumOf { it.amount }
}