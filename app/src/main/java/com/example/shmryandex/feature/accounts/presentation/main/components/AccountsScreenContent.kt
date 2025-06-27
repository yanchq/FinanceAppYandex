package com.example.shmryandex.feature.accounts.presentation.main.components

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.example.shmryandex.feature.accounts.presentation.main.contract.AccountsUIEvent
import com.example.shmryandex.feature.accounts.presentation.main.contract.AccountsUIState
import com.example.shmryandex.core.presentation.ui.AccountDropdownMenu
import com.example.shmryandex.core.presentation.ui.TopGreenCard

@Composable
fun AccountsScreenContent(
    uiState: AccountsUIState,
    setEvent: (AccountsUIEvent) -> Unit
) {
    Column {
        AccountDropdownMenu(
            accounts = uiState.accounts,
            selectedAccount = uiState.selectedAccount,
            onAccountSelected = { setEvent(AccountsUIEvent.SelectAccount(it)) }
        )
        uiState.selectedAccount?.let { account ->
            TopGreenCard(
                title = account.name,
                amount = uiState.selectedAccount.balance,
                currency = account.currency,
                canNavigate = true,
                onNavigateClick = {},
                avatarEmoji = "\uD83D\uDCB0"
            )

            TopGreenCard(
                title = "Валюта",
                currency = account.currency,
                canNavigate = true,
                onNavigateClick = {},
            )
        }
    }
}