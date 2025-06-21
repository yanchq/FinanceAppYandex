package com.example.shmryandex.presentation.navigation

import com.example.shmryandex.R

sealed class Screen(
    val route: String,
    val title: String = "",
    val topAppBarIcon: Int? = null,
    val hasFloatingActionButton: Boolean = false
) {

    object Expenses: Screen(
        EXPENSES_ROUTE,
        EXPENSES_TITLE,
        R.drawable.ic_history,
        true
    )
    object Incomes: Screen(
        INCOMES_ROUTE,
        INCOMES_TITLE,
        R.drawable.ic_history,
        true
    )
    object Account: Screen(
        ACCOUNT_ROUTE,
        ACCOUNT_TITLE,
        R.drawable.ic_edit,
        true
    )
    object Categories: Screen(
        CATEGORIES_ROUTE,
        CATEGORIES_TITLE,
        null,
        false
    )
    object Options: Screen(
        OPTIONS_ROUTE,
        OPTIONS_TITLE,
        null,
        false
    )
    object Splash: Screen(
        SPLASH_ROUTE
    )
    object ExpensesHistory: Screen(
        EXPENSES_HISTORY_ROUTE,
        EXPENSES_HISTORY_TITLE
    )
    object IncomesHistory: Screen(
        INCOMES_HISTORY_ROUTE,
        INCOMES_HISTORY_TITLE
    )
    object AddAccount: Screen(
        ADD_ACCOUNT_ROUTE,
        ADD_ACCOUNT_TITLE
    )

    companion object {
        const val EXPENSES_ROUTE = "Expenses"
        const val INCOMES_ROUTE = "Incomes"
        const val ACCOUNT_ROUTE = "Account"
        const val CATEGORIES_ROUTE = "Categories"
        const val OPTIONS_ROUTE = "Options"
        const val SPLASH_ROUTE = "Splash"
        const val EXPENSES_HISTORY_ROUTE = "ExpensesHistory"
        const val ADD_ACCOUNT_ROUTE = "AddAccount"
        const val INCOMES_HISTORY_ROUTE = "IncomesHistory"

        const val EXPENSES_TITLE = "Расходы сегодня"
        const val INCOMES_TITLE = "Доходы сегодня"
        const val ACCOUNT_TITLE = "Мой счет"
        const val CATEGORIES_TITLE = "Мои статьи"
        const val OPTIONS_TITLE = "Настройки"
        const val EXPENSES_HISTORY_TITLE = "История расходов"
        const val ADD_ACCOUNT_TITLE = "Создать счет"
        const val INCOMES_HISTORY_TITLE = "История доходов"

        const val EXPENSES_GRAPH_ROUTE = "ExpensesGraphRoute"
        const val INCOMES_GRAPH_ROUTE = "IncomesGraphRoute"
        const val ACCOUNT_GRAPH_ROUTE = "AccountGraphRoute"
        const val CATEGORIES_GRAPH_ROUTE = "CategoriesGraphRoute"
        const val OPTIONS_GRAPH_ROUTE = "OptionsGraphRoute"
    }
}