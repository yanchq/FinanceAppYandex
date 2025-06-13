package com.example.shmryandex.presentation.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun AppNavGraph(
    navHostController: NavHostController,
    expensesScreenContent: @Composable () -> Unit,
    incomesScreenContent: @Composable () -> Unit,
    accountScreenContent: @Composable () -> Unit,
    categoriesScreenContent: @Composable () -> Unit,
    optionsScreenContent: @Composable () -> Unit
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.Expenses.route,
        enterTransition = {
            EnterTransition.None
        },
        exitTransition = {
            ExitTransition.None
        }
    ) {
        composable(Screen.Expenses.route) {
            expensesScreenContent()
        }
        composable(Screen.Incomes.route) {
            incomesScreenContent()
        }
        composable(Screen.Account.route) {
            accountScreenContent()
        }
        composable(Screen.Categories.route) {
            categoriesScreenContent()
        }
        composable(Screen.Options.route) {
            optionsScreenContent()
        }
    }
}