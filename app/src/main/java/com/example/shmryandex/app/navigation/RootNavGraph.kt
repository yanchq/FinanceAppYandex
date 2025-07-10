package com.example.shmryandex.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.shmryandex.app.presentation.main.screen.MainScreen
import com.example.shmryandex.app.presentation.splash.screen.SplashScreen

/**
 * Корневой навигационный граф приложения.
 * Определяет навигацию между сплеш-скрином и основным экраном.
 */
@Composable
fun RootNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(Screen.Splash.route) {
            SplashScreen(navController)
        }

        composable("main") {
            MainScreen()
        }
    }

}
