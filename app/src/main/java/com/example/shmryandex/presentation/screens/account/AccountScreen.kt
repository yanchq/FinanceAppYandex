package com.example.shmryandex.presentation.screens.account

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.shmryandex.R
import com.example.shmryandex.ui.toCurrencyString
import com.example.shmryandex.ui.AccountDropdownMenu
import com.example.shmryandex.ui.TopGreenCard
import com.example.shmryandex.ui.theme.DividerGrey
import com.example.shmryandex.ui.theme.SecondaryGreen

@Composable
fun AccountScreen(viewModel: AccountViewModel = hiltViewModel()) {

    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    val accounts = viewModel.loadAccount().collectAsStateWithLifecycle()
    Column() {
        AccountDropdownMenu(
            accounts = accounts.value,
            selectedAccount = uiState.value.selectedAccount,
            onAccountSelected = { viewModel.onIntent(AccountIntent.SelectAccount(it)) }
        )
        uiState.value.selectedAccount?.let { account ->
            TopGreenCard(
                title = account.name,
                amount = uiState.value.selectedAccount!!.balance,
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

