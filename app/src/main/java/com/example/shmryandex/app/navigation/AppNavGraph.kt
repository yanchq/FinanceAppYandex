package com.example.shmryandex.app.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import com.example.shmryandex.core.domain.entity.Account
import com.google.gson.Gson
import java.net.URLDecoder

/**
 * Основной навигационный граф приложения.
 * Определяет навигацию между всеми основными экранами приложения.
 * Поддерживает вложенную навигацию для каждого раздела.
 */
@Composable
fun AppNavGraph(
    navHostController: NavHostController,
    expensesScreenContent: @Composable () -> Unit,
    incomesScreenContent: @Composable () -> Unit,
    incomesHistoryScreenContent: @Composable () -> Unit,
    accountScreenContent: @Composable () -> Unit,
    addAccountScreenContent: @Composable () -> Unit,
    editAccountScreenContent: @Composable (Account) -> Unit,
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
            composable(
                route = "${Screen.ExpensesHistory.route}/{${Screen.HISTORY_ARGUMENT}}",
                arguments = listOf(
                    navArgument(Screen.HISTORY_ARGUMENT) { type = NavType.BoolType }
                )
            ) {
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
            composable(
                route = "${Screen.IncomesHistory.route}/{${Screen.HISTORY_ARGUMENT}}",
                arguments = listOf(
                    navArgument(Screen.HISTORY_ARGUMENT) { type = NavType.BoolType }
                )
            ) {
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
            composable(
                route = "${Screen.EditAccount.route}/{${Screen.EDIT_ACCOUNT_ARGUMENT}}",
                arguments = listOf(
                    navArgument(Screen.EDIT_ACCOUNT_ARGUMENT) { type = NavType.StringType }
                )
            ) { backStackEntry ->
                val encoded = backStackEntry.arguments?.getString(Screen.EDIT_ACCOUNT_ARGUMENT)
                val accountJson = URLDecoder.decode(encoded, "UTF-8")
                val account = Gson().fromJson(accountJson, Account::class.java)
                editAccountScreenContent(account)
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