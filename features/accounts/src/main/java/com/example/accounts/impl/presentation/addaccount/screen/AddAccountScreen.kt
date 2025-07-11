package com.example.accounts.impl.presentation.addaccount.screen


import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.core.presentation.LocalViewModelFactory

import com.example.accounts.impl.presentation.addaccount.components.AddAccountScreenContent
import com.example.accounts.impl.presentation.addaccount.contract.AddAccountUIEffect
import com.example.accounts.impl.presentation.addaccount.viewmodel.AddAccountViewModel

/**
 * Composable функция экрана добавления нового счета.
 * Отображает форму для ввода данных счета и обрабатывает взаимодействие
 * с пользователем, включая отображение уведомлений о результате операций.
 */
@Composable
fun AddAccountScreen(viewModel: AddAccountViewModel = viewModel(factory = LocalViewModelFactory.current)) {

    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(uiState) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is AddAccountUIEffect.ShowErrorSnackbar -> {
                    snackbarHostState.showSnackbar(
                        message = effect.message,
                        actionLabel = effect.color,
                        withDismissAction = true,
                        duration = SnackbarDuration.Short
                    )
                }
                is AddAccountUIEffect.ShowSuccessSnackbar -> {
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

    AddAccountScreenContent(
        uiState = uiState.value,
        snackbarHostState = snackbarHostState,
        sendEvent = { event ->
            viewModel.onEvent(event)
        }
    )
}