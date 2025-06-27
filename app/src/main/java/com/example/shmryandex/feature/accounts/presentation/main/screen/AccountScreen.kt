package com.example.shmryandex.feature.accounts.presentation.main.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.shmryandex.feature.accounts.presentation.main.components.AccountsScreenContent
import com.example.shmryandex.feature.accounts.presentation.main.viewmodel.AccountsViewModel
import com.example.shmryandex.core.presentation.ui.AccountDropdownMenu
import com.example.shmryandex.core.presentation.ui.TopGreenCard

@Composable
fun AccountScreen(viewModel: AccountsViewModel = hiltViewModel()) {

    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    AccountsScreenContent(
        uiState.value
    ) { event ->
        viewModel.onEvent(event)
    }
}

