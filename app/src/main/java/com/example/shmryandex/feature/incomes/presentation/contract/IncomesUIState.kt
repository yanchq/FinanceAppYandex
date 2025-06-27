package com.example.shmryandex.feature.incomes.presentation.contract

import com.example.shmryandex.core.presentation.mvi.UIState
import com.example.shmryandex.feature.incomes.domain.entity.Income

data class IncomesUIState(
    val incomes: List<Income> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
): UIState {
    val summary: Double
        get() = incomes.sumOf { it.amount }
}
