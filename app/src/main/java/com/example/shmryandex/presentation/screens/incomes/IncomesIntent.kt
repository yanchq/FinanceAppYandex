package com.example.shmryandex.presentation.screens.incomes

import com.example.shmryandex.presentation.screens.expenses.ExpensesIntent

sealed interface IncomesIntent {
    data object RefreshIncomes : IncomesIntent
}