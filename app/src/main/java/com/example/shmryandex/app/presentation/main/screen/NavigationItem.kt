package com.example.shmryandex.app.presentation.main.screen

import com.example.shmryandex.R
import com.example.shmryandex.app.navigation.Screen

/**
 * Sealed класс, представляющий элементы нижней навигации.
 * Содержит информацию о маршруте экрана, метке и иконке для каждого пункта меню.
 */
sealed class NavigationItem(
    val screen: String,
    val label: Int,
    val iconId: Int
) {
    data object Expenses: NavigationItem(
        Screen.EXPENSES_GRAPH_ROUTE,
        R.string.expenses_nav_item_title,
        R.drawable.expenses
    )

    data object Incomes: NavigationItem(
        Screen.INCOMES_GRAPH_ROUTE,
        R.string.incomes_nav_item_title,
        R.drawable.incomes
    )

    data object Account: NavigationItem(
        Screen.ACCOUNT_GRAPH_ROUTE,
        R.string.accounts_nav_item_title,
        R.drawable.account
    )
    data object Categories: NavigationItem(
        Screen.CATEGORIES_GRAPH_ROUTE,
        R.string.categories_nav_item_title,
        R.drawable.categories
    )
    data object Options: NavigationItem(
        Screen.OPTIONS_GRAPH_ROUTE,
        R.string.options_nav_item_title,
        R.drawable.options
    )
}