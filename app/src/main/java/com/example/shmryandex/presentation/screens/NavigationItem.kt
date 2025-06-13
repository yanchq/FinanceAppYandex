package com.example.shmryandex.presentation.screens

import com.example.shmryandex.R
import com.example.shmryandex.presentation.navigation.Screen

sealed class NavigationItem(
    val screen: Screen,
    val label: String,
    val iconId: Int
) {
    data object Expenses: NavigationItem(
        Screen.Expenses,
        "Расходы",
        R.drawable.expenses
    )

    data object Incomes: NavigationItem(
        Screen.Incomes,
        "Доходы",
        R.drawable.incomes
    )

    data object Account: NavigationItem(
        Screen.Account,
        "Счет",
        R.drawable.account
    )
    data object Categories: NavigationItem(
        Screen.Categories,
        "Статьи",
        R.drawable.categories
    )
    data object Options: NavigationItem(
        Screen.Options,
        "Настройки",
        R.drawable.options
    )
}