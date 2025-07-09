package com.example.shmryandex.app.presentation.components

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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.shmryandex.R
import com.example.shmryandex.app.navigation.AppNavGraph
import com.example.shmryandex.app.navigation.Screen
import com.example.shmryandex.app.presentation.contract.MainUIEvent
import com.example.shmryandex.app.presentation.contract.MainUIState
import com.example.shmryandex.app.presentation.screen.NavigationItem
import com.example.shmryandex.core.presentation.ui.theme.PrimaryGreen
import com.example.shmryandex.feature.accounts.presentation.addaccount.screen.AddAccountScreen
import com.example.shmryandex.feature.accounts.presentation.editaccount.screen.EditAccountScreen
import com.example.shmryandex.feature.accounts.presentation.main.screen.AccountScreen
import com.example.shmryandex.feature.categories.presentation.screen.CategoriesScreen
import com.example.shmryandex.feature.expenses.presentation.screen.ExpensesScreen
import com.example.shmryandex.feature.history.presentation.screen.HistoryScreen
import com.example.shmryandex.feature.incomes.presentation.screen.IncomesScreen
import com.example.shmryandex.feature.options.OptionsScreen

@Composable
fun MainScreenContent(
    uiState: MainUIState,
    navHostController: NavHostController,
    sendEvent: (MainUIEvent) -> Unit,
) {

    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentNavigationBarRoute =
        navBackStackEntry?.destination?.parent?.route ?: navBackStackEntry?.destination?.route
    val currentScreenRoute =  navBackStackEntry?.destination?.route

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
        else -> Screen.Expenses
    }

    Scaffold(
        topBar = {
            CustomTopAppBar(
                title = currentScreen.title,
                icon = currentScreen.topAppBarIcon,
                onIconClick = {
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
                                text = item.label,
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
                        if (currentScreen == Screen.Account)
                            navHostController.navigate(Screen.AddAccount.route)
                    },
                    shape = CircleShape
                ) {
                    Image(painter = painterResource(R.drawable.ic_plus), contentDescription = null)
                }
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
        ) {
            AppNavGraph(
                navHostController = navHostController,
                expensesScreenContent = { ExpensesScreen() },
                expensesHistoryScreenContent = { HistoryScreen(it) },
                incomesScreenContent = { IncomesScreen() },
                incomesHistoryScreenContent = { HistoryScreen(it) },
                accountScreenContent = { AccountScreen() },
                addAccountScreenContent = { AddAccountScreen() },
                categoriesScreenContent = { CategoriesScreen() },
                optionsScreenContent = { OptionsScreen() },
                editAccountScreenContent = { EditAccountScreen() }
            )
        }
    }
}

@Composable
private fun CustomTopAppBar(
    title: String,
    icon: Int?,
    onIconClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(PrimaryGreen)
            .windowInsetsPadding(WindowInsets.statusBars)
            .height(64.dp)
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
            textAlign = TextAlign.Center,
            text = title,
            style = MaterialTheme.typography.titleLarge
        )

        icon?.let {
            IconButton(
                onClick = onIconClick,
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