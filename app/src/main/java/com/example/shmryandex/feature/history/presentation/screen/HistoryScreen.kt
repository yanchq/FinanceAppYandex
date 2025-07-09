package com.example.shmryandex.feature.history.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.shmryandex.core.presentation.ui.AppCard
import com.example.shmryandex.core.presentation.ui.CustomDatePickerDialog
import com.example.shmryandex.core.presentation.ui.TopGreenCard
import com.example.shmryandex.core.utils.rememberMyViewModel
import com.example.shmryandex.feature.history.presentation.components.HistoryScreenContent
import com.example.shmryandex.feature.history.presentation.contract.HistoryUIEvent
import com.example.shmryandex.feature.history.presentation.viewmodel.HistoryViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

/**
 * Composable-функция, представляющая экран истории транзакций.
 * Отображает список транзакций за выбранный период с возможностью фильтрации и сортировки.
 * Использует ViewModel для управления состоянием и обработки событий UI.
 *
 * @param viewModel ViewModel для управления данными и логикой экрана
 */
@Composable
fun HistoryScreen(
    isIncome: Boolean,
    viewModel: HistoryViewModel = rememberMyViewModel()
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    HistoryScreenContent(
        uiState = uiState.value,
        isIncome = isIncome,
        sendEvent = { event ->
            viewModel.onEvent(event)
        }
    )
}


