package com.example.shmryandex.app.presentation.splash.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.core.presentation.LocalViewModelFactory
import com.example.shmryandex.app.navigation.Screen

import com.example.shmryandex.app.presentation.splash.components.screencontent.SplashScreenContent
import com.example.shmryandex.app.presentation.splash.contract.SplashUIEffect
import com.example.shmryandex.app.presentation.splash.viewmodel.SplashViewModel
import kotlinx.coroutines.delay

/**
 * Composable-функция, представляющая сплеш-экран приложения.
 * Отображает анимацию загрузки и обрабатывает навигацию на главный экран
 * после завершения инициализации приложения.
 */
@Composable
fun SplashScreen(
    navController: NavHostController,
    viewModel: SplashViewModel = viewModel(factory = LocalViewModelFactory.current)) {

    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(viewModel.effect) {
        viewModel.effect.collect { effect ->
            when (effect) {
                SplashUIEffect.NavigateToMainScreen -> {
                    delay(200)
                    navController.navigate("main") {
                        popUpTo(Screen.Splash.route) { inclusive = true }
                    }
                }
            }
        }
    }

    SplashScreenContent(uiState.value) { event ->
        viewModel.onEvent(event)
    }
}