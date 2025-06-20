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
    accountScreenContent: @Composable () -> Unit,
    addAccountScreenContent: @Composable () -> Unit,
    categoriesScreenContent: @Composable () -> Unit,
    optionsScreenContent: @Composable () -> Unit,
    expensesHistoryScreenContent: @Composable () -> Unit
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
        navigation(
            startDestination = "expenses_main",
            route = Screen.Expenses.route
        ) {
            composable("expenses_main") {
                expensesScreenContent()
            }
            composable(Screen.ExpensesHistory.route) {
                expensesHistoryScreenContent()
            }
        }

        navigation(
            startDestination = "incomes_main",
            route = Screen.Incomes.route
        ) {
            composable("incomes_main") {
                incomesScreenContent()
            }
        }

        navigation(
            startDestination = "account_main",
            route = Screen.Account.route
        ) {
            composable("account_main") {
                accountScreenContent()
            }
            composable(Screen.AddAccount.route) {
                addAccountScreenContent()
            }
        }

        navigation(
            startDestination = "categories_main",
            route = Screen.Categories.route
        ) {
            composable("categories_main") {
                categoriesScreenContent()
            }
        }

        navigation(
            startDestination = "options_main",
            route = Screen.Options.route
        ) {
            composable("options_main") {
                optionsScreenContent()
            }
        }
    }
}