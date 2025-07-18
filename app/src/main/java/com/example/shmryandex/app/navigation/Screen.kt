package com.example.shmryandex.app.navigation

import com.example.shmryandex.R

/**
 * Sealed класс, представляющий различные экраны приложения.
 * Содержит информацию о маршруте, заголовке, иконке в топ-баре и наличии плавающей кнопки действия.
 */
sealed class Screen(
    val route: String,
    val title: String = "",
    val topAppBarIcon: Int? = null,
    val hasFloatingActionButton: Boolean = false,
    val leftTopAppBarIcon: Int? = null
) {

    data object Expenses: Screen(
        EXPENSES_ROUTE,
        EXPENSES_TITLE,
        R.drawable.ic_history,
        true
    )
    data object Incomes: Screen(
        INCOMES_ROUTE,
        INCOMES_TITLE,
        R.drawable.ic_history,
        true
    )
    data object Account: Screen(
        ACCOUNT_ROUTE,
        ACCOUNT_TITLE,
        R.drawable.ic_edit,
        true
    )
    data object Categories: Screen(
        CATEGORIES_ROUTE,
        CATEGORIES_TITLE,
        null,
        false
    )
    data object Options: Screen(
        OPTIONS_ROUTE,
        OPTIONS_TITLE,
        null,
        false
    )
    data object Splash: Screen(
        SPLASH_ROUTE
    )
    data object ExpensesHistory: Screen(
        EXPENSES_HISTORY_ROUTE,
        EXPENSES_HISTORY_TITLE,
        R.drawable.ic_analysis,
        false,
        R.drawable.ic_cancel
    )
    data object IncomesHistory: Screen(
        INCOMES_HISTORY_ROUTE,
        INCOMES_HISTORY_TITLE,
        R.drawable.ic_analysis,
        false,
        R.drawable.ic_cancel
    )
    data object AddAccount: Screen(
        ADD_ACCOUNT_ROUTE,
        ADD_ACCOUNT_TITLE,
        null,
        false,
        R.drawable.ic_cancel
    )
    data object EditAccount: Screen(
        EDIT_ACCOUNT_ROUTE,
        EDIT_ACCOUNT_TITLE,
        null,
        false,
        R.drawable.ic_cancel
    )
    data object AddExpense: Screen(
        ADD_EXPENSE_ROUTE,
        ADD_EXPENSE_TITLE,
        null,
        false,
        R.drawable.ic_cancel
    )
    data object AddIncome: Screen(
        ADD_INCOME_ROUTE,
        ADD_INCOME_TITLE,
        null,
        false,
        R.drawable.ic_cancel
    )
    data object EditIncome: Screen(
        EDIT_INCOME_ROUTE,
        EDIT_INCOME_TITLE,
        null,
        false,
        R.drawable.ic_cancel
    )
    data object EditExpense: Screen(
        EDIT_EXPENSE_ROUTE,
        EDIT_EXPENSE_TITLE,
        null,
        false,
        R.drawable.ic_cancel
    )
    data object ExpensesAnalytics: Screen(
        EXPENSES_ANALYTICS_ROUTE,
        EXPENSES_ANALYTICS_TITLE,
        null,
        false,
        R.drawable.ic_cancel
    )
    data object IncomesAnalytics: Screen(
        INCOMES_ANALYTICS_ROUTE,
        INCOMES_ANALYTICS_TITLE,
        null,
        false,
        R.drawable.ic_cancel
    )


    companion object {
        const val HISTORY_ARGUMENT = "isIncome"
        const val ADD_TRANSACTION_ARGUMENT = "isIncome"
        const val EDIT_TRANSACTION_ARGUMENT = "transaction"

        const val EXPENSES_ROUTE = "Expenses"
        const val INCOMES_ROUTE = "Incomes"
        const val ACCOUNT_ROUTE = "Account"
        const val CATEGORIES_ROUTE = "Categories"
        const val OPTIONS_ROUTE = "Options"
        const val SPLASH_ROUTE = "Splash"
        const val EXPENSES_HISTORY_ROUTE = "ExpensesHistory"
        const val ADD_ACCOUNT_ROUTE = "AddAccount"
        const val INCOMES_HISTORY_ROUTE = "IncomesHistory"
        const val EDIT_ACCOUNT_ROUTE = "EditAccount"
        const val ADD_EXPENSE_ROUTE = "AddExpense"
        const val ADD_INCOME_ROUTE = "AddIncome"
        const val EDIT_EXPENSE_ROUTE = "EditExpense"
        const val EDIT_INCOME_ROUTE = "EditIncome"
        const val EXPENSES_ANALYTICS_ROUTE = "ExpensesAnalytics"
        const val INCOMES_ANALYTICS_ROUTE = "IncomesAnalytics"

        const val EXPENSES_TITLE = "Расходы сегодня"
        const val INCOMES_TITLE = "Доходы сегодня"
        const val ACCOUNT_TITLE = "Мой счет"
        const val CATEGORIES_TITLE = "Мои статьи"
        const val OPTIONS_TITLE = "Настройки"
        const val EXPENSES_HISTORY_TITLE = "История расходов"
        const val ADD_ACCOUNT_TITLE = "Создать счет"
        const val INCOMES_HISTORY_TITLE = "История доходов"
        const val EDIT_ACCOUNT_TITLE = "Изменить счет"
        const val ADD_EXPENSE_TITLE = "Добавить расход"
        const val ADD_INCOME_TITLE = "Добавить доход"
        const val EDIT_EXPENSE_TITLE = "Изменить расход"
        const val EDIT_INCOME_TITLE = "Изменить доход"
        const val INCOMES_ANALYTICS_TITLE = "Анализ доходов"
        const val EXPENSES_ANALYTICS_TITLE = "Анализ расходов"

        const val EXPENSES_GRAPH_ROUTE = "ExpensesGraphRoute"
        const val INCOMES_GRAPH_ROUTE = "IncomesGraphRoute"
        const val ACCOUNT_GRAPH_ROUTE = "AccountGraphRoute"
        const val CATEGORIES_GRAPH_ROUTE = "CategoriesGraphRoute"
        const val OPTIONS_GRAPH_ROUTE = "OptionsGraphRoute"
    }
}