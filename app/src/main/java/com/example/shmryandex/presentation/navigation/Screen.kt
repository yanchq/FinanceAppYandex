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

    companion object {

        const val EXPENSES_ROUTE = "Expenses"
        const val INCOMES_ROUTE = "Incomes"
        const val ACCOUNT_ROUTE = "Account"
        const val CATEGORIES_ROUTE = "Categories"
        const val OPTIONS_ROUTE = "Options"
        const val SPLASH_ROUTE = "Splash"

        const val EXPENSES_TITLE = "Расходы сегодня"
        const val INCOMES_TITLE = "Доходы сегодня"
        const val ACCOUNT_TITLE = "Мой счет"
        const val CATEGORIES_TITLE = "Мои статьи"
        const val OPTIONS_TITLE = "Настройки"
    }
}