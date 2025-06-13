package com.example.shmryandex.presentation

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
import com.example.shmryandex.presentation.navigation.AppNavGraph
import com.example.shmryandex.presentation.navigation.Screen
import com.example.shmryandex.presentation.screens.NavigationItem
import com.example.shmryandex.presentation.screens.account.AccountScreen
import com.example.shmryandex.presentation.screens.categories.CategoriesScreen
import com.example.shmryandex.presentation.screens.expenses.ExpensesScreen
import com.example.shmryandex.presentation.screens.incomes.IncomesScreen
import com.example.shmryandex.presentation.screens.options.OptionsScreen
import com.example.shmryandex.ui.theme.PrimaryGreen

@Composable
fun MainScreen() {

    val navHostController = rememberNavController()

    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val currentScreen = when (currentRoute) {
        Screen.Expenses.route -> Screen.Expenses
        Screen.Incomes.route -> Screen.Incomes
        Screen.Account.route -> Screen.Account
        Screen.Categories.route -> Screen.Categories
        Screen.Options.route -> Screen.Options
        else -> Screen.Expenses
    }

    Scaffold(
        topBar = {
            CustomTopAppBar(currentScreen.title, currentScreen.topAppBarIcon)
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
                        selected = currentRoute == item.screen.route,
                        onClick = {
                            if (currentRoute != item.screen.route) {
                                navHostController.navigate(item.screen.route) {
                                    launchSingleTop = true
                                    popUpTo(navHostController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    restoreState = true
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
                    onClick = {},
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
                incomesScreenContent = { IncomesScreen() },
                accountScreenContent = { AccountScreen() },
                categoriesScreenContent = { CategoriesScreen() },
                optionsScreenContent = { OptionsScreen() },
            )
        }
    }
}

@Composable
private fun CustomTopAppBar(title: String, icon: Int?) {
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
                onClick = {},
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