package com.example.shmryandex.feature.accounts.presentation.addaccount.screen


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
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.shmryandex.feature.accounts.presentation.addaccount.components.AddAccountScreenContent
import com.example.shmryandex.feature.accounts.presentation.addaccount.contract.AddAccountUIEffect
import com.example.shmryandex.feature.accounts.presentation.addaccount.viewmodel.AddAccountViewModel
import com.example.shmryandex.core.presentation.ui.AccountNameInput
import com.example.shmryandex.core.presentation.ui.BalanceInput
import com.example.shmryandex.core.presentation.ui.CurrencyBottomSheet
import com.example.shmryandex.core.presentation.ui.CurrencyOption
import com.example.shmryandex.core.presentation.ui.CurrencySelectorButton
import com.example.shmryandex.core.presentation.ui.theme.PrimaryGreen
import kotlinx.coroutines.launch

/**
 * Composable функция экрана добавления нового счета.
 * Отображает форму для ввода данных счета и обрабатывает взаимодействие
 * с пользователем, включая отображение уведомлений о результате операций.
 */
@Composable
fun AddAccountScreen(viewModel: AddAccountViewModel = hiltViewModel()) {

    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(uiState) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is AddAccountUIEffect.ShowErrorSnackbar -> {
                    snackbarHostState.showSnackbar(
                        message = effect.message,
                        actionLabel = effect.color,
                        withDismissAction = true,
                        duration = SnackbarDuration.Short
                    )
                }
                is AddAccountUIEffect.ShowSuccessSnackbar -> {
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

    AddAccountScreenContent(
        uiState = uiState.value,
        snackbarHostState = snackbarHostState,
        sendEvent = { event ->
            viewModel.onEvent(event)
        }
    )
}