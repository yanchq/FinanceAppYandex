package com.example.history.impl.presentation.main.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.core.R
import com.example.core.utils.ui.AppCard
import com.example.core.utils.ui.CustomDatePickerDialog
import com.example.core.utils.ui.TopGreenCard
import com.example.core.utils.formatDateToMillis
import com.example.core.utils.formatDateToString
import com.example.core.utils.ui.localizedString
import com.example.history.impl.presentation.main.contract.HistoryUIEvent
import com.example.history.impl.presentation.main.contract.HistoryUIState

@Composable
fun HistoryScreenContent(
    onItemClicked: (Int) -> Unit,
    uiState: HistoryUIState,
    sendEvent: (HistoryUIEvent) -> Unit,
    isIncome: Boolean
) {
    var showDialog by remember { mutableStateOf(false) }
    var currentPicker by remember { mutableStateOf(DateType.START) }

    LaunchedEffect(Unit) {
        sendEvent(
            HistoryUIEvent.StartDateSelected(
                startDate = uiState.startDate,
                isIncome = isIncome
            )
        )
    }

    Column {

        TopGreenCard(
            title = localizedString(R.string.start),
            currency = uiState.startDate,
            onNavigateClick = {
                currentPicker = DateType.START
                showDialog = true
            }
        )

        TopGreenCard(
            title = localizedString(R.string.end),
            currency = uiState.endDate,
            onNavigateClick = {
                currentPicker = DateType.END
                showDialog = true
            }
        )

        TopGreenCard(
            title = localizedString(R.string.total_amount),
            amount = uiState.totalAmount,
            currency = "₽"
        )

        LazyColumn {
            items(
                items = uiState.items,
            ) { historyItem ->
                AppCard(
                    title = historyItem.name,
                    amount = historyItem.amount,
                    currency = historyItem.currency,
                    subAmount = historyItem.createdAt,
                    avatarEmoji = historyItem.emoji,
                    subtitle = historyItem.comment,
                    canNavigate = true,
                    onNavigateClick = {
                        onItemClicked(historyItem.id.toInt())
                    },
                )
            }
        }

        if (showDialog) {
            CustomDatePickerDialog(
                initialDate = when (currentPicker) {
                    DateType.START -> uiState.startDate.formatDateToMillis()
                    DateType.END -> uiState.endDate.formatDateToMillis()
                },
                onDismissRequest = { showDialog = false },
                onClear = {

                },
                onDateSelected = {
                    when (currentPicker) {
                        DateType.START -> {
                            sendEvent(
                                HistoryUIEvent.StartDateSelected(
                                    startDate = it!!.formatDateToString(),
                                    isIncome = isIncome
                                )
                            )
                        }

                        DateType.END -> {
                            sendEvent(
                                HistoryUIEvent.EndDateSelected(
                                    endDate = it!!.formatDateToString(),
                                    isIncome = isIncome
                                )
                            )
                        }
                    }
                }
            )
        }
    }
}

enum class DateType {
    START, END
}