package com.example.history.impl.presentation.analytics.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.example.core.R
import com.example.core.presentation.mvi.ScreenContent
import com.example.core.utils.formatDateToMillis
import com.example.core.utils.formatDateToString
import com.example.core.utils.ui.AppCard
import com.example.core.utils.ui.CustomDatePickerDialog
import com.example.core.utils.ui.LoadingScreen
import com.example.core.utils.ui.TopGreenCard
import com.example.core.utils.ui.localizedString
import com.example.graphs.PieGraph
import com.example.history.impl.presentation.analytics.contract.AnalyticsUIEvent
import com.example.history.impl.presentation.analytics.contract.AnalyticsUIState
import com.example.history.impl.presentation.analytics.utils.generateColorsHSV
import com.example.history.impl.presentation.main.components.DateType
import com.example.history.impl.presentation.main.contract.HistoryUIEvent

@Composable
fun AnalyticsScreenContent(
    uiState: AnalyticsUIState,
    isIncome: Boolean,
    sendEvent: (AnalyticsUIEvent) -> Unit
) {
    var showDialog by remember { mutableStateOf(false) }
    var currentPicker by remember { mutableStateOf(DateType.START) }
    val colors = generateColorsHSV(uiState.items.count())

    LaunchedEffect(Unit) {
        sendEvent(
            AnalyticsUIEvent.StartDateSelected(
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

        if (uiState.items.isNotEmpty()) {

            val dataMap = mutableMapOf<String, Float>()
            uiState.items.forEach {
                dataMap.put(it.emoji + " " + it.name, it.percentage.toFloat())
            }
            PieGraph(
                data = dataMap,
                colors = colors
            )
        }

        when (uiState.screenContent) {
            ScreenContent.Content -> {

                if (uiState.items.isNotEmpty()) {
                    LazyColumn {
                        items(
                            items = uiState.items,
                        ) { historyItem ->
                            AnalyticsCard(
                                analyticsItem = historyItem,
                                color = colors.getOrNull(uiState.items.indexOf(historyItem))
                            )
                        }
                    }
                } else {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                    ) {
                        Text(
                            modifier = Modifier
                                .align(Alignment.Center)
                                .fillMaxWidth(),
                            text = "Транзакций нет",
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }

            ScreenContent.Error -> {}
            ScreenContent.Loading -> {
                LoadingScreen()
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
                                AnalyticsUIEvent.StartDateSelected(
                                    startDate = it!!.formatDateToString(),
                                    isIncome = isIncome
                                )
                            )
                        }

                        DateType.END -> {
                            sendEvent(
                                AnalyticsUIEvent.EndDateSelected(
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