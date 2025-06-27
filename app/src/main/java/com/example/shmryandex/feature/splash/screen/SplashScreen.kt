package com.example.shmryandex.feature.splash.screen

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.*
import com.example.shmryandex.app.navigation.Screen
import com.example.shmryandex.feature.splash.components.screencontent.SplashScreenContent
import com.example.shmryandex.feature.splash.contract.SplashUIEffect
import com.example.shmryandex.feature.splash.viewmodel.SplashViewModel
import kotlinx.coroutines.delay

/**
 * Composable-функция, представляющая сплеш-экран приложения.
 * Отображает анимацию загрузки и обрабатывает навигацию на главный экран
 * после завершения инициализации приложения.
 */
@Composable
fun SplashScreen(
    navController: NavHostController,
    viewModel: SplashViewModel = hiltViewModel()) {

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