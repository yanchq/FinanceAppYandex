package com.example.accounts.impl.presentation.main.screen

import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.core.presentation.LocalViewModelFactory

import com.example.accounts.impl.presentation.main.components.AccountsScreenContent
import com.example.accounts.impl.presentation.main.viewmodel.AccountsViewModel

@Composable
fun AccountScreen(viewModel: AccountsViewModel = viewModel(factory = LocalViewModelFactory.current)) {

    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    AccountsScreenContent(
        uiState.value
    ) { event ->
        viewModel.onEvent(event)
    }
}

