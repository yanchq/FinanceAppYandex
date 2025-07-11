package com.example.history.impl.presentation.screen

import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.core.presentation.LocalViewModelFactory

import com.example.history.impl.presentation.components.HistoryScreenContent
import com.example.history.impl.presentation.viewmodel.HistoryViewModel

/**
 * Composable-функция, представляющая экран истории транзакций.
 * Отображает список транзакций за выбранный период с возможностью фильтрации и сортировки.
 * Использует ViewModel для управления состоянием и обработки событий UI.
 *
 * @param viewModel ViewModel для управления данными и логикой экрана
 */
@Composable
fun HistoryScreen(
    onItemClicked: (Int) -> Unit,
    isIncome: Boolean,
    viewModel: HistoryViewModel = viewModel(factory = LocalViewModelFactory.current)
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    HistoryScreenContent(
        uiState = uiState.value,
        isIncome = isIncome,
        sendEvent = { event ->
            viewModel.onEvent(event)
        },
        onItemClicked = {
            onItemClicked(it)
        }
    )
}


