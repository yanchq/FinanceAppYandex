package com.example.shmryandex.feature.accounts.presentation.addaccount.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.shmryandex.feature.accounts.presentation.addaccount.contract.AddAccountUIEvent
import com.example.shmryandex.feature.accounts.presentation.addaccount.contract.AddAccountUIState
import com.example.shmryandex.core.presentation.ui.AccountNameInput
import com.example.shmryandex.core.presentation.ui.BalanceInput
import com.example.shmryandex.core.presentation.ui.CurrencyBottomSheet
import com.example.shmryandex.core.presentation.ui.CurrencyOption
import com.example.shmryandex.core.presentation.ui.CurrencySelectorButton
import com.example.shmryandex.core.presentation.ui.CustomSnackbar
import com.example.shmryandex.core.presentation.ui.theme.PrimaryGreen
import com.example.shmryandex.feature.accounts.presentation.addaccount.contract.AddAccountUIEffect
import kotlinx.coroutines.launch

/**
 * Composable функция контента экрана добавления счета.
 * Отображает форму с полями ввода названия счета, баланса и выбора валюты.
 * Включает валидацию полей и кнопку создания счета.
 * @param uiState текущее состояние UI
 * @param sendEvent функция для отправки событий в ViewModel
 * @param snackbarHostState состояние для отображения уведомлений
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddAccountScreenContent(
    uiState: AddAccountUIState,
    sendEvent: (AddAccountUIEvent) -> Unit,
    snackbarHostState: SnackbarHostState
) {
    var accountName by remember { mutableStateOf("") }
    var balance by remember { mutableStateOf("") }
    var selectedCurrency by remember { mutableStateOf<CurrencyOption?>(null) }

    val isFormValid by remember(accountName, balance, selectedCurrency) {
        derivedStateOf {
            accountName.isNotBlank() &&
                    balance.isNotBlank() &&
                    selectedCurrency != null
        }
    }


    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )
    val coroutineScope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }

    if (showBottomSheet) {
        CurrencyBottomSheet(
            onDismiss = {
                coroutineScope.launch {
                    sheetState.hide()
                    showBottomSheet = false
                }
            },
            onCurrencySelected = {
                selectedCurrency = it
                coroutineScope.launch {
                    sheetState.hide()
                    showBottomSheet = false
                }
            },
            sheetState = sheetState
        )
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        AccountNameInput(
            accountName = accountName,
            onNameChange = { accountName = it },
            isError = accountName.isBlank()
        )
        BalanceInput(
            balance = balance,
            onBalanceChange = { balance = it },
            isError = balance.isBlank()
        )
        Spacer(Modifier.height(12.dp))
        CurrencySelectorButton(
            selectedCurrency = selectedCurrency,
            onClick = {
                coroutineScope.launch {
                    showBottomSheet = true
                    sheetState.show()
                }
            }
        )
        Spacer(Modifier.height(20.dp))
        Button(
            onClick = {
                sendEvent(
                    AddAccountUIEvent.AddAccount(
                        name = accountName,
                        balance = balance,
                        currency = selectedCurrency!!.code
                    )
                )
            },
            shape = RoundedCornerShape(percent = 50),
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            enabled = isFormValid,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimary,
                disabledContainerColor = MaterialTheme.colorScheme.surface,
                disabledContentColor = MaterialTheme.colorScheme.onSurface
            )
        ) {
            Text(text = "Создать счёт")
        }

        SnackbarHost(
            hostState = snackbarHostState,
        ) { data ->
            val backgroundColor = when (data.visuals.actionLabel) {
                AddAccountUIEffect.SUCCESS_COLOR -> PrimaryGreen
                AddAccountUIEffect.ERROR_COLOR -> Color.Red
                else -> MaterialTheme.colorScheme.surface
            }

            CustomSnackbar(
                snackbarData = data,
                backgroundColor = backgroundColor
            )
        }
    }
}