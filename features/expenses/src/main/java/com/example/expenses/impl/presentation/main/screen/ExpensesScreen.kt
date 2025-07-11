package com.example.expenses.impl.presentation.main.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.core.presentation.LocalViewModelFactory

import com.example.expenses.impl.presentation.main.components.ExpensesScreenContent
import com.example.expenses.impl.presentation.main.viewmodel.ExpensesViewModel

/**
 * Composable функция экрана расходов.
 * Отображает список расходных операций с их суммами и категориями.
 * Использует ExpensesViewModel для управления данными и состоянием.
 */
@Composable
fun ExpensesScreen(
    onItemClicked: (Int) -> Unit,
    viewModel: ExpensesViewModel = viewModel(factory = LocalViewModelFactory.current)
) {

    val uiState = viewModel.uiState.collectAsState()

    ExpensesScreenContent(
        onItemClicked = {
            onItemClicked(it)
        },
        uiState =  uiState.value,
    ) { event ->
        viewModel.onEvent(event)
    }
}

