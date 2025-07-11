package com.example.incomes.impl.presentation.contract

import com.example.core.presentation.mvi.UIState
import com.example.incomes.impl.domain.entity.Income

/**
 * Состояние UI для экрана доходов.
 * Содержит список доходов, состояние загрузки, информацию об ошибках и общую сумму доходов.
 */
data class IncomesUIState(
    val incomes: List<Income> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
): UIState {
    val summary: Double
        get() = incomes.sumOf { it.amount }
}
