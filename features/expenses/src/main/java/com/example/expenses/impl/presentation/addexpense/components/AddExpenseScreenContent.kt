package com.example.expenses.impl.presentation.addexpense.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.core.utils.formatDateToMillis
import com.example.core.utils.formatDateToString
import com.example.core.utils.formatToString
import com.example.core.utils.toLocalTime
import com.example.core.utils.ui.AccountDropdownMenu
import com.example.core.utils.ui.AmountInputField
import com.example.core.utils.ui.AppCard
import com.example.core.utils.ui.CategoriesDropdownMenu
import com.example.core.utils.ui.CustomDatePickerDialog
import com.example.core.utils.ui.CustomSnackbar
import com.example.core.utils.ui.CustomTimePickerDialog
import com.example.core.utils.ui.SingleLineTextField
import com.example.core.utils.ui.localizedString
import com.example.core.utils.ui.theme.Grey
import com.example.core.utils.ui.theme.PrimaryGreen
import com.example.core.utils.ui.theme.TextBlack
import com.example.expenses.R
import com.example.expenses.impl.presentation.addexpense.contract.AddExpenseUIEffect
import com.example.expenses.impl.presentation.addexpense.contract.AddExpenseUIEvent
import com.example.expenses.impl.presentation.addexpense.contract.AddExpenseUIState
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@Composable
fun AddExpenseScreenContent(
    uiState: AddExpenseUIState,
    snackbarHostState: SnackbarHostState,
    sendEvent: (AddExpenseUIEvent) -> Unit
) {

    var showDialog by remember { mutableStateOf(false) }
    var showTimeDialog by remember { mutableStateOf(false) }

    val isFormValid by remember(uiState.amount) {
        derivedStateOf {
            uiState.amount.isNotBlank()
        }
    }

    Column {
        AccountDropdownMenu(
            accounts = uiState.accountsList,
            selectedAccount = uiState.account,
            onAccountSelected = {
                sendEvent(AddExpenseUIEvent.AccountsSelected(it))
            }
        )
        CategoriesDropdownMenu(
            categories = uiState.categoriesList,
            selectedCategory = uiState.category,
            onAccountSelected = {
                sendEvent(AddExpenseUIEvent.CategoryChanged(it))
            }
        )
        AmountInputField(
            amount = uiState.amount,
            onAmountChange = {
                sendEvent(AddExpenseUIEvent.AmountChanged(it))
            },
            currency = uiState.account?.currency ?: "",
        )
        AppCard(
            title = localizedString(com.example.core.R.string.date),
            onNavigateClick = {
                showDialog = true
            },
            stringDate = uiState.transactionDate
        )
        AppCard(
            title = localizedString(com.example.core.R.string.time),
            onNavigateClick = {
                showTimeDialog = true
            },
            stringDate = uiState.transactionTime
        )
        SingleLineTextField(
            value = uiState.comment,
            onValueChange = {
                sendEvent(AddExpenseUIEvent.CommentChanged(it))
            },
            placeholder = localizedString(com.example.core.R.string.comment)
        )

        Spacer(
            modifier = Modifier
                .height(50.dp)
        )

        Button(
            onClick = {
                sendEvent(
                    AddExpenseUIEvent.AddExpenseEvent
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
                disabledContainerColor = Grey,
                disabledContentColor = MaterialTheme.colorScheme.onSurface
            )
        ) {
            Text(
                text = localizedString(R.string.edit_transaction),
                color = TextBlack
            )
        }

        Spacer(
            modifier = Modifier
                .height(50.dp)
        )

        SnackbarHost(
            hostState = snackbarHostState,
        ) { data ->
            val backgroundColor = when (data.visuals.actionLabel) {
                AddExpenseUIEffect.SUCCESS_COLOR -> PrimaryGreen
                AddExpenseUIEffect.ERROR_COLOR -> Color.Red
                else -> MaterialTheme.colorScheme.surface
            }

            CustomSnackbar(
                snackbarData = data,
                backgroundColor = backgroundColor
            )
        }
        if (showDialog) {
            CustomDatePickerDialog(
                initialDate = uiState.transactionDate.formatDateToMillis(),
                onDismissRequest = { showDialog = false },
                onClear = {
                },
                onDateSelected = { selectedDate ->
                    sendEvent(AddExpenseUIEvent.DateChanged(selectedDate!!.formatDateToString()))
                },
            )
        }
        if (showTimeDialog) {
            CustomTimePickerDialog(
                initialTime = uiState.transactionTime.toLocalTime(),
                onDismissRequest = { showTimeDialog = false },
                onClear = {
                },
                onTimeSelected = { selected ->
                    sendEvent(AddExpenseUIEvent.TimeChanged(selected.formatToString()))
                }
            )
        }
    }
}
