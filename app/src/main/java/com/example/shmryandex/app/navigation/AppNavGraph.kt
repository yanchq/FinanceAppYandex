package com.example.shmryandex.app.navigation

import android.util.Log
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.accounts.impl.di.DaggerAccountsComponent
import com.example.accounts.impl.presentation.addaccount.screen.AddAccountScreen
import com.example.accounts.impl.presentation.editaccount.screen.EditAccountScreen
import com.example.accounts.impl.presentation.main.screen.AccountScreen
import com.example.categories.impl.di.DaggerCategoriesComponent
import com.example.core.presentation.LocalViewModelFactory
import com.example.expenses.impl.di.DaggerExpensesComponent
import com.example.history.impl.di.DaggerHistoryComponent
import com.example.incomes.impl.di.DaggerIncomesComponent
import com.example.shmryandex.app.presentation.options.buildinfo.AppInfoScreen
import com.example.shmryandex.app.presentation.options.changelocale.ChangeLocaleScreen
import com.example.shmryandex.app.presentation.options.haptic.HapticScreen
import com.example.shmryandex.app.presentation.options.maincolor.ChangeMainColorScreen
import com.example.shmryandex.app.presentation.options.pincode.PinCodeScreen
import com.example.shmryandex.app.presentation.options.sync.SyncScreen
import com.example.shmryandex.appComponent

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
    incomesHistoryScreenContent: @Composable (Boolean) -> Unit,
    categoriesScreenContent: @Composable () -> Unit,
    optionsScreenContent: @Composable () -> Unit,
    expensesHistoryScreenContent: @Composable (Boolean) -> Unit,
    addTransactionScreenContent: @Composable (Boolean) -> Unit,
    editTransactionScreenContent: @Composable (Int) -> Unit,
    expensesAnalyticsScreenContent: @Composable (Boolean) -> Unit,
    incomesAnalyticsScreenContent: @Composable (Boolean) -> Unit,
) {

    val context = LocalContext.current
    val appComponent = context.appComponent

    val expensesComponent = DaggerExpensesComponent.factory().create(appComponent)

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
                CompositionLocalProvider(
                    LocalViewModelFactory provides expensesComponent.viewModelFactory()
                ) {
                    expensesScreenContent()
                }
            }

            composable(
                route = "${Screen.AddExpense.route}/{${Screen.ADD_TRANSACTION_ARGUMENT}}",
                arguments = listOf(
                    navArgument(Screen.ADD_TRANSACTION_ARGUMENT) { type = NavType.BoolType }
                )
            ) { backStackEntry ->

                val isIncome = backStackEntry.arguments?.getBoolean("isIncome") ?: false

                CompositionLocalProvider(
                    LocalViewModelFactory provides expensesComponent.viewModelFactory()
                ) {
                    addTransactionScreenContent(isIncome)
                }
            }

            composable(
                route = "${Screen.EditExpense.route}/{${Screen.EDIT_TRANSACTION_ARGUMENT}}",
                arguments = listOf(
                    navArgument(Screen.EDIT_TRANSACTION_ARGUMENT) { type = NavType.IntType }
                )
            ) { backStackEntry ->

                val transactionId =
                    backStackEntry.arguments?.getInt(Screen.EDIT_TRANSACTION_ARGUMENT) ?: 0

                CompositionLocalProvider(
                    LocalViewModelFactory provides expensesComponent.viewModelFactory()
                ) {
                    editTransactionScreenContent(transactionId)
                }
            }

            composable(
                route = "${Screen.ExpensesHistory.route}/{${Screen.HISTORY_ARGUMENT}}",
                arguments = listOf(
                    navArgument(Screen.HISTORY_ARGUMENT) { type = NavType.BoolType }
                )
            ) { backStackEntry ->

                val historyComponent = remember {
                    DaggerHistoryComponent.factory().create(appComponent)
                }
                val isIncome = backStackEntry.arguments?.getBoolean("isIncome") ?: false

                CompositionLocalProvider(
                    LocalViewModelFactory provides historyComponent.viewModelFactory()
                ) {
                    expensesHistoryScreenContent(isIncome)
                }
            }

            composable(
                route = "${Screen.ExpensesAnalytics.route}/{${Screen.HISTORY_ARGUMENT}}",
                arguments = listOf(
                    navArgument(Screen.HISTORY_ARGUMENT) { type = NavType.BoolType }
                )
            ) { backStackEntry ->

                val historyComponent = remember {
                    DaggerHistoryComponent.factory().create(appComponent)
                }
                val isIncome = backStackEntry.arguments?.getBoolean("isIncome") ?: false

                CompositionLocalProvider(
                    LocalViewModelFactory provides historyComponent.viewModelFactory()
                ) {
                    expensesAnalyticsScreenContent(isIncome)
                }
            }
        }

        navigation(
            startDestination = Screen.Incomes.route,
            route = Screen.INCOMES_GRAPH_ROUTE
        ) {

            val incomesComponent =
                DaggerIncomesComponent.factory().create(appComponent)

            composable(Screen.Incomes.route) {
                CompositionLocalProvider(
                    LocalViewModelFactory provides incomesComponent.viewModelFactory()
                ) {
                    incomesScreenContent()
                }
            }

            composable(
                route = "${Screen.AddIncome.route}/{${Screen.ADD_TRANSACTION_ARGUMENT}}",
                arguments = listOf(
                    navArgument(Screen.ADD_TRANSACTION_ARGUMENT) { type = NavType.BoolType }
                )
            ) { backStackEntry ->

                val isIncome = backStackEntry.arguments?.getBoolean("isIncome") ?: false

                CompositionLocalProvider(
                    LocalViewModelFactory provides expensesComponent.viewModelFactory()
                ) {
                    addTransactionScreenContent(isIncome)
                }
            }

            composable(
                route = "${Screen.EditIncome.route}/{${Screen.EDIT_TRANSACTION_ARGUMENT}}",
                arguments = listOf(
                    navArgument(Screen.EDIT_TRANSACTION_ARGUMENT) { type = NavType.IntType }
                )
            ) { backStackEntry ->

                val transactionId =
                    backStackEntry.arguments?.getInt(Screen.EDIT_TRANSACTION_ARGUMENT) ?: 0

                CompositionLocalProvider(
                    LocalViewModelFactory provides expensesComponent.viewModelFactory()
                ) {
                    editTransactionScreenContent(transactionId)
                }
            }

            composable(
                route = "${Screen.IncomesHistory.route}/{${Screen.HISTORY_ARGUMENT}}",
                arguments = listOf(
                    navArgument(Screen.HISTORY_ARGUMENT) { type = NavType.BoolType }
                )
            ) { backStackEntry ->

                val historyComponent = remember {
                    DaggerHistoryComponent.factory().create(appComponent)
                }
                val isIncome = backStackEntry.arguments?.getBoolean("isIncome") ?: false

                CompositionLocalProvider(
                    LocalViewModelFactory provides historyComponent.viewModelFactory()
                ) {
                    incomesHistoryScreenContent(isIncome)
                }
            }

            composable(
                route = "${Screen.IncomesAnalytics.route}/{${Screen.HISTORY_ARGUMENT}}",
                arguments = listOf(
                    navArgument(Screen.HISTORY_ARGUMENT) { type = NavType.BoolType }
                )
            ) { backStackEntry ->

                val historyComponent = remember {
                    DaggerHistoryComponent.factory().create(appComponent)
                }
                val isIncome = backStackEntry.arguments?.getBoolean("isIncome") ?: false

                CompositionLocalProvider(
                    LocalViewModelFactory provides historyComponent.viewModelFactory()
                ) {
                    incomesAnalyticsScreenContent(isIncome)
                }
            }
        }

        navigation(
            startDestination = Screen.Account.route,
            route = Screen.ACCOUNT_GRAPH_ROUTE
        ) {
            val accountsComponent =
                DaggerAccountsComponent.factory().create(appComponent)

            composable(Screen.Account.route) {
                Log.d("ScopesTest", "Navigate accounts")
                CompositionLocalProvider(
                    LocalViewModelFactory provides accountsComponent.viewModelFactory()
                ) {
                    AccountScreen()
                }
            }
            composable(Screen.AddAccount.route) {
                CompositionLocalProvider(
                    LocalViewModelFactory provides accountsComponent.viewModelFactory()
                ) {
                    AddAccountScreen()
                }
            }
            composable(Screen.EditAccount.route) {
                CompositionLocalProvider(
                    LocalViewModelFactory provides accountsComponent.viewModelFactory()
                ) {
                    EditAccountScreen()
                }
            }
        }

        navigation(
            startDestination = Screen.Categories.route,
            route = Screen.CATEGORIES_GRAPH_ROUTE
        ) {
            composable(Screen.Categories.route) {
                val categoriesComponent = remember {
                    DaggerCategoriesComponent.factory().create(appComponent)
                }

                CompositionLocalProvider(
                    LocalViewModelFactory provides categoriesComponent.viewModelFactory()
                ) {
                    categoriesScreenContent()
                }
            }
        }

        navigation(
            startDestination = Screen.Options.route,
            route = Screen.OPTIONS_GRAPH_ROUTE
        ) {
            composable(Screen.Options.route) {
                optionsScreenContent()
            }
            composable(Screen.AppInfo.route) {
                AppInfoScreen()
            }
            composable(Screen.ChangeLocale.route) {
                ChangeLocaleScreen()
            }
            composable(Screen.ChangeMainColor.route) {
                ChangeMainColorScreen()
            }
            composable(Screen.SyncInterval.route) {
                SyncScreen()
            }
            composable(Screen.SelectHaptic.route) {
                HapticScreen()
            }
            composable(Screen.PinCode.route) {
                PinCodeScreen(navHostController)
            }
        }
    }
}