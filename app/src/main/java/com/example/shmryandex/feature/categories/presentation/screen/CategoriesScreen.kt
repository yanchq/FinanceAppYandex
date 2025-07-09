package com.example.shmryandex.feature.categories.presentation.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.example.shmryandex.core.utils.rememberMyViewModel
import com.example.shmryandex.feature.categories.presentation.components.CategoriesScreenContent
import com.example.shmryandex.feature.categories.presentation.viewmodel.CategoriesViewModel

/**
 * Composable функция экрана категорий.
 * Отображает список категорий транзакций с возможностью их выбора.
 * Использует CategoriesViewModel для управления данными и состоянием.
 */
@Composable
fun CategoriesScreen(viewModel: CategoriesViewModel = rememberMyViewModel()) {

    val uiState = viewModel.uiState.collectAsState()

    CategoriesScreenContent(
        uiState.value
    ) { event ->
        viewModel.onEvent(event)
    }
}
