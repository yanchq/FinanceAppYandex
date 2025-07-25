package com.example.accounts.impl.presentation.main.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.accounts.R
import com.example.accounts.impl.presentation.main.contract.AccountsUIEvent
import com.example.accounts.impl.presentation.main.contract.AccountsUIState
import com.example.core.utils.ui.AccountDropdownMenu
import com.example.core.utils.ui.TopGreenCard
import com.example.core.utils.ui.localizedString
import com.example.graphs.BarGraph

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
                title = localizedString(R.string.currency),
                currency = account.currency,
                canNavigate = true,
                onNavigateClick = {},
            )

            Spacer(
                modifier = Modifier.height(50.dp)
            )
            uiState.graphInfo?.let {
                BarGraph(
                    month = it.first,
                    values = it.second
                )
            }
        }
    }
}