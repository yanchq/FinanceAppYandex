package com.example.shmryandex.presentation.screens.incomes.history

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.shmryandex.R
import com.example.shmryandex.domain.entity.Income
import com.example.shmryandex.presentation.screens.expenses.history.DateType
import com.example.shmryandex.ui.AppCard
import com.example.shmryandex.ui.CustomDatePickerDialog
import com.example.shmryandex.ui.TopGreenCard
import com.example.shmryandex.ui.toCurrencyString
import com.example.shmryandex.ui.theme.DividerGrey
import com.example.shmryandex.ui.theme.SecondaryGreen
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale


@Composable
fun IncomesHistoryScreen(viewModel: IncomesHistoryViewModel = hiltViewModel()) {

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
        viewModel.loadIncomesByPeriod(
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
                items = uiState.value.incomes,
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
