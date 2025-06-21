package com.example.shmryandex.presentation.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.shmryandex.presentation.screens.account.AccountScreen
import com.example.shmryandex.presentation.screens.categories.CategoriesScreen
import com.example.shmryandex.presentation.screens.expenses.ExpensesScreen
import com.example.shmryandex.presentation.screens.expenses.history.ExpensesHistoryScreen
import com.example.shmryandex.presentation.screens.incomes.IncomesScreen
import com.example.shmryandex.presentation.screens.options.OptionsScreen

@Composable
fun AppNavGraph(
    navHostController: NavHostController,
    expensesScreenContent: @Composable () -> Unit,
    incomesScreenContent: @Composable () -> Unit,
    incomesHistoryScreenContent: @Composable () -> Unit,
    accountScreenContent: @Composable () -> Unit,
    addAccountScreenContent: @Composable () -> Unit,
    categoriesScreenContent: @Composable () -> Unit,
    optionsScreenContent: @Composable () -> Unit,
    expensesHistoryScreenContent: @Composable () -> Unit
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.EXPENSES_GRAPH_ROUTE,
        enterTransition = {
            EnterTransition.None
        },
        exitTransition = {
            ExitTransition.None
        }
    ) {
        navigation(
            startDestination = Screen.Expenses.route,
            route = Screen.EXPENSES_GRAPH_ROUTE
        ) {
            composable(Screen.Expenses.route) {
                expensesScreenContent()
            }
            composable(Screen.ExpensesHistory.route) {
                expensesHistoryScreenContent()
            }
        }

        navigation(
            startDestination = Screen.Incomes.route,
            route = Screen.INCOMES_GRAPH_ROUTE
        ) {
            composable(Screen.Incomes.route) {
                incomesScreenContent()
            }
            composable(Screen.IncomesHistory.route) {
                incomesHistoryScreenContent()
            }
        }

        navigation(
            startDestination = Screen.Account.route,
            route = Screen.ACCOUNT_GRAPH_ROUTE
        ) {
            composable(Screen.Account.route) {
                accountScreenContent()
            }
            composable(Screen.AddAccount.route) {
                addAccountScreenContent()
            }
        }

        navigation(
            startDestination = Screen.Categories.route,
            route = Screen.CATEGORIES_GRAPH_ROUTE
        ) {
            composable(Screen.Categories.route) {
                categoriesScreenContent()
            }
        }

        navigation(
            startDestination = Screen.Options.route,
            route = Screen.OPTIONS_GRAPH_ROUTE
        ) {
            composable(Screen.Options.route) {
                optionsScreenContent()
            }
        }
    }
}