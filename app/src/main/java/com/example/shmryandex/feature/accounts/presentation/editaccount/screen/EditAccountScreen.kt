package com.example.shmryandex.feature.accounts.presentation.editaccount.screen

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.shmryandex.feature.accounts.presentation.addaccount.components.AddAccountScreenContent
import com.example.shmryandex.feature.accounts.presentation.addaccount.contract.AddAccountUIEffect
import com.example.shmryandex.feature.accounts.presentation.editaccount.components.EditAccountScreenContent
import com.example.shmryandex.feature.accounts.presentation.editaccount.contract.EditAccountUIEffect
import com.example.shmryandex.feature.accounts.presentation.editaccount.viewmodel.EditAccountViewModel

@Composable
fun EditAccountScreen(viewModel: EditAccountViewModel = hiltViewModel()) {
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