package com.example.shmryandex.presentation.screens.incomes

import com.example.shmryandex.presentation.screens.expenses.ExpensesIntent

sealed class IncomesIntent {

    object LoadIncomes: IncomesIntent()
}