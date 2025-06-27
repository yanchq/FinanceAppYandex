package com.example.shmryandex.feature.history.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.shmryandex.core.presentation.ui.AppCard
import com.example.shmryandex.core.presentation.ui.CustomDatePickerDialog
import com.example.shmryandex.core.presentation.ui.TopGreenCard
import com.example.shmryandex.feature.history.presentation.components.HistoryScreenContent
import com.example.shmryandex.feature.history.presentation.viewmodel.HistoryViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

@Composable
fun HistoryScreen(
    viewModel: HistoryViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

   HistoryScreenContent(uiState.value) { event ->
       viewModel.onEvent(event)
   }
}


