package com.example.shmryandex.app.presentation.main.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.core.presentation.LocalViewModelFactory
import com.example.shmryandex.app.navigation.Screen
import com.example.shmryandex.app.presentation.main.components.MainScreenContent
import com.example.shmryandex.app.presentation.main.contract.MainUIEffect
import com.example.shmryandex.app.presentation.main.viewmodel.MainViewModel


/**
 * Основной экран приложения.
 * Содержит нижнюю навигационную панель, верхний бар и контейнер для контента.
 * Управляет навигацией между основными разделами приложения.
 */
@Composable
fun MainScreen(viewModel: MainViewModel = viewModel(factory = LocalViewModelFactory.current)) {

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

