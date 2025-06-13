package com.example.shmryandex.presentation.screens.incomes

import com.example.shmryandex.domain.entity.Income

data class IncomesUiState(
    val incomes: List<Income> = emptyList(),
    val summary: Int = 0
)