package com.example.history.impl.presentation.analytics.screen

import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.core.presentation.LocalViewModelFactory
import com.example.history.impl.presentation.analytics.components.AnalyticsScreenContent
import com.example.history.impl.presentation.analytics.viewmodel.AnalyticsViewModel

@Composable
fun AnalyticsScreen(
    isIncome: Boolean,
    viewModel: AnalyticsViewModel = viewModel(factory = LocalViewModelFactory.current)
) {

    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    AnalyticsScreenContent(
        uiState = uiState.value,
        isIncome = isIncome
    ) { event ->
        viewModel.onEvent(event)
    }
}