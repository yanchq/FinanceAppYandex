package com.example.expenses.impl.presentation.addexpense.contract

import com.example.core.presentation.mvi.UIEffect

sealed interface AddExpenseUIEffect: UIEffect {
    data class ShowSuccessSnackbar(
        val message: String = "Счет успешно добавлен",
        val color: String = SUCCESS_COLOR
    ): AddExpenseUIEffect

    data class ShowErrorSnackbar(
        val message: String = "Произошла ошибка",
        val color: String = ERROR_COLOR
    ): AddExpenseUIEffect

    companion object {
        const val ERROR_COLOR = "Error"
        const val SUCCESS_COLOR = "Success"
    }
}