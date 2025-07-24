package com.example.shmryandex.app.navigation

import com.example.shmryandex.R

/**
 * Sealed класс, представляющий различные экраны приложения.
 * Содержит информацию о маршруте, заголовке, иконке в топ-баре и наличии плавающей кнопки действия.
 */
sealed class Screen(
    val route: String,
    val title: Int = 0,
    val topAppBarIcon: Int? = null,
    val hasFloatingActionButton: Boolean = false,
    val leftTopAppBarIcon: Int? = null
) {

    data object Expenses: Screen(
        EXPENSES_ROUTE,
        R.string.expenses_title,
        R.drawable.ic_history,
        true
    )
    data object Incomes: Screen(
        INCOMES_ROUTE,
        R.string.incomes_title,
        R.drawable.ic_history,
        true
    )
    data object Account: Screen(
        ACCOUNT_ROUTE,
        R.string.accounts_title,
        R.drawable.ic_edit,
        true
    )
    data object Categories: Screen(
        CATEGORIES_ROUTE,
        R.string.categories_title,
        null,
        false
    )
    data object Options: Screen(
        OPTIONS_ROUTE,
        R.string.options_title,
        null,
        false
    )
    data object Splash: Screen(
        SPLASH_ROUTE
    )
    data object ExpensesHistory: Screen(
        EXPENSES_HISTORY_ROUTE,
        R.string.expenses_history_title,
        R.drawable.ic_analysis,
        false,
        R.drawable.ic_cancel
    )
    data object IncomesHistory: Screen(
        INCOMES_HISTORY_ROUTE,
        R.string.incomes_history_title,
        R.drawable.ic_analysis,
        false,
        R.drawable.ic_cancel
    )
    data object AddAccount: Screen(
        ADD_ACCOUNT_ROUTE,
        R.string.add_account_title,
        null,
        false,
        R.drawable.ic_cancel
    )
    data object EditAccount: Screen(
        EDIT_ACCOUNT_ROUTE,
        R.string.edit_account_title,
        null,
        false,
        R.drawable.ic_cancel
    )
    data object AddExpense: Screen(
        ADD_EXPENSE_ROUTE,
        R.string.add_expense_title,
        null,
        false,
        R.drawable.ic_cancel
    )
    data object AddIncome: Screen(
        ADD_INCOME_ROUTE,
        R.string.add_incomes_title,
        null,
        false,
        R.drawable.ic_cancel
    )
    data object EditIncome: Screen(
        EDIT_INCOME_ROUTE,
        R.string.edit_income_title,
        null,
        false,
        R.drawable.ic_cancel
    )
    data object EditExpense: Screen(
        EDIT_EXPENSE_ROUTE,
        R.string.edit_expense_title,
        null,
        false,
        R.drawable.ic_cancel
    )
    data object ExpensesAnalytics: Screen(
        EXPENSES_ANALYTICS_ROUTE,
        R.string.expenses_analytics_title,
        null,
        false,
        R.drawable.ic_cancel
    )
    data object IncomesAnalytics: Screen(
        INCOMES_ANALYTICS_ROUTE,
        R.string.incomes_analytics_title,
        null,
        false,
        R.drawable.ic_cancel
    )
    data object AppInfo: Screen(
        APP_INFO_ROUTE,
        R.string.app_info_title,
        null,
        false,
        R.drawable.ic_cancel
    )
    data object ChangeLocale: Screen(
        CHANGE_LOCALE_ROUTE,
        R.string.change_locale_title,
        null,
        false,
        R.drawable.ic_cancel
    )
    data object ChangeMainColor: Screen(
        CHANGE_MAIN_COLOR_ROUTE,
        R.string.change_main_color_title,
        null,
        false,
        R.drawable.ic_cancel
    )
    data object SyncInterval: Screen(
        SYNC_INTERVAL_ROUTE,
        R.string.sync_title,
        null,
        false,
        R.drawable.ic_cancel
    )
    data object SelectHaptic: Screen(
        SELECT_HAPTIC_ROUTE,
        R.string.haptics_title,
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
        const val APP_INFO_ROUTE = "AppInfoRoute"
        const val CHANGE_LOCALE_ROUTE = "ChangeLocaleRoute"
        const val CHANGE_MAIN_COLOR_ROUTE = "ChangeMainColorRoute"
        const val SYNC_INTERVAL_ROUTE = "SyncIntervalRoute"
        const val SELECT_HAPTIC_ROUTE = "SelectHapticRoute"

        const val APP_INFO_TITLE = "О программе"
        const val CHANGE_LOCALE_TITLE = "Язык"
        const val CHANGE_MAIN_COLOR_TITLE = "Язык"
        const val SYNC_INTERVAL_TITLE = "Синхронизация"
        const val SELECT_HAPTIC_TITLE = "Хаптики"

        const val EXPENSES_GRAPH_ROUTE = "ExpensesGraphRoute"
        const val INCOMES_GRAPH_ROUTE = "IncomesGraphRoute"
        const val ACCOUNT_GRAPH_ROUTE = "AccountGraphRoute"
        const val CATEGORIES_GRAPH_ROUTE = "CategoriesGraphRoute"
        const val OPTIONS_GRAPH_ROUTE = "OptionsGraphRoute"
    }
}