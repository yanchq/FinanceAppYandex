package com.example.shmryandex.app.presentation.main.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.shmryandex.R
import com.example.shmryandex.app.navigation.AppNavGraph
import com.example.shmryandex.app.navigation.Screen
import com.example.shmryandex.app.presentation.main.contract.MainUIEvent
import com.example.shmryandex.app.presentation.main.contract.MainUIState
import com.example.shmryandex.app.presentation.main.screen.NavigationItem
import com.example.core.utils.ui.theme.PrimaryGreen
import com.example.categories.impl.presentation.screen.CategoriesScreen
import com.example.core.utils.ui.localizedString
import com.example.expenses.impl.presentation.addexpense.screen.AddExpenseScreen
import com.example.expenses.impl.presentation.editexpense.screen.EditExpenseScreen
import com.example.expenses.impl.presentation.main.screen.ExpensesScreen
import com.example.history.impl.presentation.analytics.screen.AnalyticsScreen
import com.example.history.impl.presentation.main.screen.HistoryScreen
import com.example.incomes.impl.presentation.screen.IncomesScreen
import com.example.shmryandex.app.presentation.options.main.screen.OptionsScreen

@Composable
fun MainScreenContent(
    uiState: MainUIState,
    navHostController: NavHostController,
    sendEvent: (MainUIEvent) -> Unit,
    syncInfoDialogVisibility: Boolean,
    onSyncIngoDialogDismiss: () -> Unit
) {

    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentNavigationBarRoute =
        navBackStackEntry?.destination?.parent?.route ?: navBackStackEntry?.destination?.route
    val currentScreenRoute = navBackStackEntry?.destination?.route

    val currentScreen = when (currentScreenRoute) {
        Screen.Expenses.route -> Screen.Expenses
        Screen.Incomes.route -> Screen.Incomes
        Screen.Account.route -> Screen.Account
        Screen.Categories.route -> Screen.Categories
        Screen.Options.route -> Screen.Options
        "${Screen.ExpensesHistory.route}/{${Screen.HISTORY_ARGUMENT}}" -> Screen.ExpensesHistory
        Screen.AddAccount.route -> Screen.AddAccount
        "${Screen.IncomesHistory.route}/{${Screen.HISTORY_ARGUMENT}}" -> Screen.IncomesHistory
        Screen.EditAccount.route -> Screen.EditAccount
        "${Screen.AddExpense.route}/{${Screen.ADD_TRANSACTION_ARGUMENT}}" -> Screen.AddExpense
        "${Screen.AddIncome.route}/{${Screen.ADD_TRANSACTION_ARGUMENT}}" -> Screen.AddIncome
        "${Screen.EditIncome.route}/{${Screen.EDIT_TRANSACTION_ARGUMENT}}" -> Screen.EditIncome
        "${Screen.EditExpense.route}/{${Screen.EDIT_TRANSACTION_ARGUMENT}}" -> Screen.EditExpense
        "${Screen.IncomesAnalytics.route}/{${Screen.HISTORY_ARGUMENT}}" -> Screen.IncomesAnalytics
        "${Screen.ExpensesAnalytics.route}/{${Screen.HISTORY_ARGUMENT}}" -> Screen.ExpensesAnalytics
        Screen.ChangeLocale.route -> Screen.ChangeLocale
        Screen.AppInfo.route -> Screen.AppInfo
        Screen.ChangeMainColor.route -> Screen.ChangeMainColor
        Screen.SyncInterval.route -> Screen.SyncInterval
        Screen.SelectHaptic.route -> Screen.SelectHaptic
        Screen.PinCode.route -> Screen.PinCode
        else -> Screen.Expenses
    }

    Scaffold(
        topBar = {
            CustomTopAppBar(
                title = localizedString(currentScreen.title),
                rightIcon = currentScreen.topAppBarIcon,
                leftIcon = currentScreen.leftTopAppBarIcon,
                onLeftIconClick = {
                    sendEvent(MainUIEvent.HapticButtonClicked)
                    navHostController.popBackStack()
                },
                onRightIconClick = {
                    sendEvent(MainUIEvent.HapticButtonClicked)
                    when (currentScreen) {
                        Screen.Expenses -> {
                            navHostController.navigate(Screen.ExpensesHistory.route + "/false")
                        }

                        Screen.Incomes -> {
                            navHostController.navigate(Screen.IncomesHistory.route + "/true")
                        }

                        Screen.Account -> {
                            sendEvent(MainUIEvent.EditAccountIconClicked)
                        }

                        Screen.ExpensesHistory -> {
                            navHostController.navigate(Screen.ExpensesAnalytics.route + "/false")
                        }

                        Screen.IncomesHistory -> {
                            navHostController.navigate(Screen.IncomesAnalytics.route + "/true")
                        }

                        else -> {}
                    }
                }
            )
        },
        bottomBar = {
            val navigateList = listOf(
                NavigationItem.Expenses,
                NavigationItem.Incomes,
                NavigationItem.Account,
                NavigationItem.Categories,
                NavigationItem.Options
            )

            NavigationBar {
                navigateList.forEach { item ->
                    NavigationBarItem(
                        selected = currentNavigationBarRoute == item.screen,
                        onClick = {
                            sendEvent(MainUIEvent.HapticButtonClicked)
                            if (currentNavigationBarRoute != item.screen) {
                                navHostController.navigate(item.screen) {
                                    launchSingleTop = true
                                    popUpTo(navHostController.graph.findStartDestination().id) {
                                        saveState = false
                                    }
                                    restoreState = false
                                }
                            }
                        },
                        icon = {
                            Icon(
                                painter = painterResource(item.iconId),
                                contentDescription = null
                            )
                        },
                        label = {
                            Text(
                                text = localizedString(item.label),
                                fontSize = 12.sp
                            )
                        }
                    )
                }
            }
        },
        floatingActionButton = {
            if (currentScreen.hasFloatingActionButton) {
                FloatingActionButton(
                    onClick = {
                        sendEvent(MainUIEvent.HapticButtonClicked)
                        when (currentScreen) {
                            Screen.Account -> {
                                navHostController.navigate(Screen.AddAccount.route)
                            }

                            Screen.Expenses -> {
                                navHostController.navigate(Screen.AddExpense.route + "/false")
                            }

                            Screen.Incomes -> {
                                navHostController.navigate(Screen.AddIncome.route + "/true")
                            }

                            else -> {

                            }
                        }
                    },
                    shape = CircleShape
                ) {
                    Image(painter = painterResource(R.drawable.ic_plus), contentDescription = null)
                }
            }
        }
    ) { paddingValues ->

        if (syncInfoDialogVisibility) {
            SyncInfoDialog(
                uiState.syncTime,
                uiState.syncStatus
            ) {
                onSyncIngoDialogDismiss()
            }
        }


        Box(
            modifier = Modifier
                .padding(paddingValues)
        ) {
            AppNavGraph(
                navHostController = navHostController,
                expensesScreenContent = {
                    ExpensesScreen(
                        onItemClicked = { expenseId ->
                            navHostController.navigate(Screen.EditExpense.route + "/" + expenseId.toString())
                        }
                    )
                },
                expensesHistoryScreenContent = { HistoryScreen(
                    isIncome =  it,
                    onItemClicked = { expenseId ->
                        navHostController.navigate(Screen.EditExpense.route + "/" + expenseId.toString())
                    }
                ) },
                incomesScreenContent = {
                    IncomesScreen(
                        onItemClicked = { expenseId ->
                            navHostController.navigate(Screen.EditIncome.route + "/" + expenseId.toString())
                        }
                    )
                },
                incomesHistoryScreenContent = { HistoryScreen(
                    isIncome =  it,
                    onItemClicked = { expenseId ->
                        navHostController.navigate(Screen.EditIncome.route + "/" + expenseId.toString())
                    }
                ) },
                categoriesScreenContent = { CategoriesScreen() },
                optionsScreenContent = { OptionsScreen(navHostController) },
                addTransactionScreenContent = { AddExpenseScreen(it) },
                editTransactionScreenContent = { EditExpenseScreen(it) },
                expensesAnalyticsScreenContent = { AnalyticsScreen(it) },
                incomesAnalyticsScreenContent = { AnalyticsScreen(it) }
            )
        }
    }
}

@Composable
private fun CustomTopAppBar(
    title: String,
    rightIcon: Int?,
    leftIcon: Int?,
    onRightIconClick: () -> Unit = {},
    onLeftIconClick: () -> Unit = {},
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primaryContainer)
            .windowInsetsPadding(WindowInsets.statusBars)
            .height(64.dp)
    ) {

        leftIcon?.let {
            IconButton(
                onClick = onLeftIconClick,
                modifier = Modifier.align(Alignment.CenterStart)
            ) {
                Image(
                    painter = painterResource(it),
                    contentDescription = null
                )
            }
        }

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
            textAlign = TextAlign.Center,
            text = title,
            style = MaterialTheme.typography.titleLarge
        )

        rightIcon?.let {
            IconButton(
                onClick = onRightIconClick,
                modifier = Modifier.align(Alignment.CenterEnd)
            ) {
                Image(
                    painter = painterResource(it),
                    contentDescription = null
                )
            }
        }
    }
}