package com.example.categories.impl.presentation.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.core.presentation.LocalViewModelFactory

import com.example.categories.impl.presentation.components.CategoriesScreenContent
import com.example.categories.impl.presentation.viewmodel.CategoriesViewModel

/**
 * Composable функция экрана категорий.
 * Отображает список категорий транзакций с возможностью их выбора.
 * Использует CategoriesViewModel для управления данными и состоянием.
 */
@Composable
fun CategoriesScreen(viewModel: CategoriesViewModel = viewModel(factory = LocalViewModelFactory.current)) {

    val uiState = viewModel.uiState.collectAsState()

    CategoriesScreenContent(
        uiState.value
    ) { event ->
        viewModel.onEvent(event)
    }
}
