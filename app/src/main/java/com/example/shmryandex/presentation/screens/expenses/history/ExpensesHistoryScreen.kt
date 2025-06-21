package com.example.shmryandex.presentation.screens.expenses.history

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.shmryandex.ui.AppCard
import com.example.shmryandex.ui.CustomDatePickerDialog
import com.example.shmryandex.ui.TopGreenCard
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

@Composable
fun ExpensesHistoryScreen(
    viewModel: ExpensesHistoryViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    var startDateMillis by remember {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.DAY_OF_MONTH, 1)
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        mutableLongStateOf(calendar.timeInMillis)
    }

    var endDateMillis by remember {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, 23)
        calendar.set(Calendar.MINUTE, 59)
        calendar.set(Calendar.SECOND, 59)
        calendar.set(Calendar.MILLISECOND, 999)
        mutableStateOf(calendar.timeInMillis)
    }

    var showDialog by remember { mutableStateOf(false) }
    var currentPicker by remember { mutableStateOf(DateType.START) }

    val dateFormatter = remember { SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()) }
    val formattedStartDate = startDateMillis?.let { dateFormatter.format(Date(it)) } ?: "Выберите дату"
    val formattedEndDate = endDateMillis?.let { dateFormatter.format(Date(it)) }
        ?: "Выберите дату"


    LaunchedEffect(startDateMillis, endDateMillis) {
        viewModel.loadExpensesByPeriod(
            startDate = formattedStartDate,
            endDate = formattedEndDate
        )
    }

    Column {

        TopGreenCard(
            title = "Начало",
            currency = formattedStartDate,
            onNavigateClick = {
                currentPicker = DateType.START
                showDialog = true
            }
        )

        TopGreenCard(
            title = "Конец",
            currency = formattedEndDate,
            onNavigateClick = {
                currentPicker = DateType.END
                showDialog = true
            }
        )

        TopGreenCard(
            title = "Сумма",
            amount = uiState.value.totalAmount,
            currency = "₽"
        )

        LazyColumn {
            items(
                items = uiState.value.expenses,
                key = { it.id }
            ) { expense ->
                AppCard(
                    title = expense.category.name,
                    amount = expense.amount,
                    currency = expense.currency,
                    subAmount = expense.createdAt,
                    avatarEmoji = expense.category.emoji,
                    subtitle = expense.comment,
                    canNavigate = true,
                    onNavigateClick = {},
                )
            }
        }

        if (showDialog) {
            CustomDatePickerDialog(
                initialDate = when (currentPicker) {
                    DateType.START -> startDateMillis
                    DateType.END -> endDateMillis
                },
                onDismissRequest = { showDialog = false },
                onClear = {

                },
                onDateSelected = {
                    when (currentPicker) {
                        DateType.START -> startDateMillis = it!!
                        DateType.END -> endDateMillis = it!!
                    }
                }
            )
        }
    }
}

enum class DateType {
    START, END
}
