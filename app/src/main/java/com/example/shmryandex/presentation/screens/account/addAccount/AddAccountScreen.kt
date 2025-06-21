package com.example.shmryandex.presentation.screens.account.addAccount


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
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.shmryandex.presentation.screens.account.AccountViewModel
import com.example.shmryandex.ui.AccountNameInput
import com.example.shmryandex.ui.BalanceInput
import com.example.shmryandex.ui.CurrencyBottomSheet
import com.example.shmryandex.ui.CurrencyOption
import com.example.shmryandex.ui.CurrencySelectorButton
import com.example.shmryandex.ui.theme.PrimaryGreen
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddAccountScreen(viewModel: AddAccountViewModel = hiltViewModel()) {

    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
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
                viewModel.onIntent(
                    AddAccountIntent.AddAccount(
                        name = accountName,
                        balance = balance,
                        currency = selectedCurrency!!.code
                    )
                )
            },
            shape = RoundedCornerShape(percent = 50), // скруглённые боковины
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

        uiState.value?.let {
            when (it) {
                true -> {
                    Text(
                        textAlign = TextAlign.Center,
                        text = "Аккаунт успешно создан",
                        color = PrimaryGreen
                    )
                }
                false -> {
                    Text(
                        textAlign = TextAlign.Center,
                        text = "Произошла ошибка",
                        color = Color.Red
                    )
                }
            }
        }


    }
}