package com.example.expenses.impl.presentation.addexpense.screen

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.core.presentation.LocalViewModelFactory
import com.example.expenses.impl.presentation.addexpense.components.AddExpenseScreenContent
import com.example.expenses.impl.presentation.addexpense.contract.AddExpenseUIEffect
import com.example.expenses.impl.presentation.addexpense.contract.AddExpenseUIEvent
import com.example.expenses.impl.presentation.addexpense.viewmodel.AddExpenseViewModel

@Composable
fun AddExpenseScreen(
    isIncome: Boolean,
    viewModel: AddExpenseViewModel = viewModel(factory = LocalViewModelFactory.current)
) {

    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.onEvent(AddExpenseUIEvent.IsIncomeInit(isIncome))

        viewModel.effect.collect { effect ->
            when (effect) {
                is AddExpenseUIEffect.ShowErrorSnackbar -> {
                    snackbarHostState.showSnackbar(
                        message = effect.message,
                        actionLabel = effect.color,
                        withDismissAction = true,
                        duration = SnackbarDuration.Short
                    )
                }
                is AddExpenseUIEffect.ShowSuccessSnackbar -> {
                    snackbarHostState.showSnackbar(
                        message = effect.message,
                        actionLabel = effect.color,
                        withDismissAction = true,
                        duration = SnackbarDuration.Short
                    )
                }
            }
        }
    }

    AddExpenseScreenContent(
        uiState =  uiState.value,
        snackbarHostState = snackbarHostState
    ) { event ->
        viewModel.onEvent(event)
    }
}