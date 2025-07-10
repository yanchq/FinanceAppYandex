package com.example.accounts.impl.presentation.editaccount.screen

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.core.presentation.LocalViewModelFactory

import com.example.accounts.impl.presentation.editaccount.components.EditAccountScreenContent
import com.example.accounts.impl.presentation.editaccount.contract.EditAccountUIEffect
import com.example.accounts.impl.presentation.editaccount.viewmodel.EditAccountViewModel

@Composable
fun EditAccountScreen(viewModel: EditAccountViewModel = viewModel(factory = LocalViewModelFactory.current)) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(uiState) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is EditAccountUIEffect.ShowErrorSnackbar -> {
                    snackbarHostState.showSnackbar(
                        message = effect.message,
                        actionLabel = effect.color,
                        withDismissAction = true,
                        duration = SnackbarDuration.Short
                    )
                }

                is EditAccountUIEffect.ShowSuccessSnackbar -> {
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

    EditAccountScreenContent(
        uiState = uiState.value,
        snackbarHostState = snackbarHostState,
        sendEvent = { event ->
            viewModel.onEvent(event)
        }
    )
}