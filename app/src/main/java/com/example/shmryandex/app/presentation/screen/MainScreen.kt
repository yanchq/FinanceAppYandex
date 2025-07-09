package com.example.shmryandex.app.presentation.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import com.example.shmryandex.app.navigation.Screen
import com.example.shmryandex.app.presentation.components.MainScreenContent
import com.example.shmryandex.app.presentation.contract.MainUIEffect
import com.example.shmryandex.app.presentation.viewmodel.MainViewModel
import com.example.shmryandex.core.utils.rememberMyViewModel

/**
 * Основной экран приложения.
 * Содержит нижнюю навигационную панель, верхний бар и контейнер для контента.
 * Управляет навигацией между основными разделами приложения.
 */
@Composable
fun MainScreen(viewModel: MainViewModel = rememberMyViewModel()) {

    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    val navHostController = rememberNavController()

    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is MainUIEffect.NavigateToEditAccountScreen -> {
                    navHostController.navigate(Screen.EditAccount.route)
                }
            }
        }
    }

    MainScreenContent(
        uiState = uiState.value,
        navHostController = navHostController
    ) { event ->
        viewModel.onEvent(event)
    }
}

