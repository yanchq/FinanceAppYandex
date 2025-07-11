package com.example.expenses.impl.presentation.editexpense.screen

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.core.presentation.LocalViewModelFactory
import com.example.expenses.impl.presentation.editexpense.components.EditExpenseScreenContent
import com.example.expenses.impl.presentation.editexpense.contract.EditExpenseUIEffect
import com.example.expenses.impl.presentation.editexpense.contract.EditExpenseUIEvent
import com.example.expenses.impl.presentation.editexpense.viewmodel.EditExpenseViewModel

@Composable
fun EditExpenseScreen(
    transactionId: Int,
    viewModel: EditExpenseViewModel = viewModel(factory = LocalViewModelFactory.current)
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.onEvent(EditExpenseUIEvent.TransactionInit(transactionId))

        viewModel.effect.collect { effect ->
            when (effect) {
                is EditExpenseUIEffect.ShowErrorSnackbar -> {
                    snackbarHostState.showSnackbar(
                        message = effect.message,
                        actionLabel = effect.color,
                        withDismissAction = true,
                        duration = SnackbarDuration.Short
                    )
                }
                is EditExpenseUIEffect.ShowSuccessSnackbar -> {
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

    EditExpenseScreenContent(
        uiState =  uiState.value,
        snackbarHostState = snackbarHostState
    ) { event ->
        viewModel.onEvent(event)
    }
}