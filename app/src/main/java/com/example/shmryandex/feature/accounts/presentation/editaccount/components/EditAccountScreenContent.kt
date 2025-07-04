package com.example.shmryandex.feature.accounts.presentation.editaccount.components

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
import com.example.shmryandex.core.presentation.ui.AccountNameInput
import com.example.shmryandex.core.presentation.ui.BalanceInput
import com.example.shmryandex.core.presentation.ui.CurrencyBottomSheet
import com.example.shmryandex.core.presentation.ui.CurrencyOption
import com.example.shmryandex.core.presentation.ui.CurrencySelectorButton
import com.example.shmryandex.core.presentation.ui.CustomSnackbar
import com.example.shmryandex.core.presentation.ui.theme.PrimaryGreen
import com.example.shmryandex.feature.accounts.presentation.addaccount.contract.AddAccountUIEffect
import com.example.shmryandex.feature.accounts.presentation.addaccount.contract.AddAccountUIEvent
import com.example.shmryandex.feature.accounts.presentation.editaccount.contract.EditAccountUIEvent
import com.example.shmryandex.feature.accounts.presentation.editaccount.contract.EditAccountUIState
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditAccountScreenContent(
    uiState: EditAccountUIState,
    snackbarHostState: SnackbarHostState,
    sendEvent: (EditAccountUIEvent) -> Unit
) {
    val isFormValid by remember(uiState.name, uiState.balance, uiState.currency) {
        derivedStateOf {
            uiState.name.isNotBlank() &&
                    uiState.balance.isNotBlank()
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
                sendEvent(EditAccountUIEvent.CurrencyEdited(it))
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
            accountName = uiState.name,
            onNameChange = { sendEvent(EditAccountUIEvent.NameEdited(it)) },
            isError = uiState.name.isBlank()
        )
        BalanceInput(
            balance = uiState.balance,
            onBalanceChange = { sendEvent(EditAccountUIEvent.BalanceEdited(it)) },
            isError = uiState.balance.isBlank()
        )
        Spacer(Modifier.height(12.dp))
        CurrencySelectorButton(
            selectedCurrency = uiState.currency,
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
                    EditAccountUIEvent.EditAccount(
                        name = uiState.name,
                        balance = uiState.balance,
                        currency = uiState.currency!!.code
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
            Text(text = "Изменить счёт")
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