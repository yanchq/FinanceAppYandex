package com.example.shmryandex.app.presentation.screen

import com.example.shmryandex.R
import com.example.shmryandex.app.navigation.Screen

/**
 * Sealed класс, представляющий элементы нижней навигации.
 * Содержит информацию о маршруте экрана, метке и иконке для каждого пункта меню.
 */
sealed class NavigationItem(
    val screen: String,
    val label: String,
    val iconId: Int
) {
    data object Expenses: NavigationItem(
        Screen.EXPENSES_GRAPH_ROUTE,
        "Расходы",
        R.drawable.expenses
    )

    data object Incomes: NavigationItem(
        Screen.INCOMES_GRAPH_ROUTE,
        "Доходы",
        R.drawable.incomes
    )

    data object Account: NavigationItem(
        Screen.ACCOUNT_GRAPH_ROUTE,
        "Счет",
        R.drawable.account
    )
    data object Categories: NavigationItem(
        Screen.CATEGORIES_GRAPH_ROUTE,
        "Статьи",
        R.drawable.categories
    )
    data object Options: NavigationItem(
        Screen.OPTIONS_GRAPH_ROUTE,
        "Настройки",
        R.drawable.options
    )
}