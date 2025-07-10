package com.example.expenses.impl.presentation.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.core.presentation.LocalViewModelFactory

import com.example.expenses.impl.presentation.components.ExpensesScreenContent
import com.example.expenses.impl.presentation.viewmodel.ExpensesViewModel

/**
 * Composable функция экрана расходов.
 * Отображает список расходных операций с их суммами и категориями.
 * Использует ExpensesViewModel для управления данными и состоянием.
 */
@Composable
fun ExpensesScreen(viewModel: ExpensesViewModel = viewModel(factory = LocalViewModelFactory.current)) {

    val uiState = viewModel.uiState.collectAsState()

    ExpensesScreenContent(
        uiState.value,
    ) { event ->
        viewModel.onEvent(event)
    }
}

