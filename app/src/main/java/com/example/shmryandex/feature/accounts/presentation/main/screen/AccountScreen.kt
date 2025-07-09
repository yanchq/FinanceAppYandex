package com.example.shmryandex.feature.accounts.presentation.main.screen

import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.shmryandex.core.utils.rememberMyViewModel
import com.example.shmryandex.feature.accounts.presentation.main.components.AccountsScreenContent
import com.example.shmryandex.feature.accounts.presentation.main.viewmodel.AccountsViewModel

@Composable
fun AccountScreen(viewModel: AccountsViewModel = rememberMyViewModel()) {

    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    AccountsScreenContent(
        uiState.value
    ) { event ->
        viewModel.onEvent(event)
    }
}

