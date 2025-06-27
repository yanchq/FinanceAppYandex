package com.example.shmryandex.feature.history.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.shmryandex.core.presentation.ui.AppCard
import com.example.shmryandex.core.presentation.ui.CustomDatePickerDialog
import com.example.shmryandex.core.presentation.ui.TopGreenCard
import com.example.shmryandex.feature.history.presentation.contract.HistoryUIEvent
import com.example.shmryandex.feature.history.presentation.contract.HistoryUIState

@Composable
fun HistoryScreenContent(
    uiState: HistoryUIState,
    sendEvent: (HistoryUIEvent) -> Unit
) {
    var showDialog by remember { mutableStateOf(false) }
    var currentPicker by remember { mutableStateOf(DateType.START) }

    Column {

        TopGreenCard(
            title = "Начало",
            currency = uiState.startDate,
            onNavigateClick = {
                currentPicker = DateType.START
                showDialog = true
            }
        )

        TopGreenCard(
            title = "Конец",
            currency = uiState.endDate,
            onNavigateClick = {
                currentPicker = DateType.END
                showDialog = true
            }
        )

        TopGreenCard(
            title = "Сумма",
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
                    onNavigateClick = {},
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
                            sendEvent(HistoryUIEvent.StartDateSelected(it!!.formatDateToString()))
                        }

                        DateType.END -> {
                            sendEvent(HistoryUIEvent.EndDateSelected(it!!.formatDateToString()))
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