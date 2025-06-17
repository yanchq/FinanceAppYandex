package com.example.shmryandex.presentation.screens.expenses

sealed interface ExpensesIntent {
    data object RefreshExpenses : ExpensesIntent
}