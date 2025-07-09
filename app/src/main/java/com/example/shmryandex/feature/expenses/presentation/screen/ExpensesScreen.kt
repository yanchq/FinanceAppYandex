package com.example.shmryandex.feature.expenses.presentation.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.example.shmryandex.core.utils.rememberMyViewModel
import com.example.shmryandex.feature.expenses.presentation.components.ExpensesScreenContent
import com.example.shmryandex.feature.expenses.presentation.viewmodel.ExpensesViewModel

/**
 * Composable функция экрана расходов.
 * Отображает список расходных операций с их суммами и категориями.
 * Использует ExpensesViewModel для управления данными и состоянием.
 */
@Composable
fun ExpensesScreen(viewModel: ExpensesViewModel = rememberMyViewModel()) {

    val uiState = viewModel.uiState.collectAsState()

    ExpensesScreenContent(
        uiState.value,
    ) { event ->
        viewModel.onEvent(event)
    }
}

