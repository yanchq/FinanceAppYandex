package com.example.expenses.impl.presentation.editexpense.contract

import com.example.core.presentation.mvi.UIEffect

sealed interface EditExpenseUIEffect: UIEffect {
    data class ShowSuccessSnackbar(
        val message: String = "Транзакция успешно изменена",
        val color: String = SUCCESS_COLOR
    ): EditExpenseUIEffect

    data class ShowErrorSnackbar(
        val message: String = "Произошла ошибка",
        val color: String = ERROR_COLOR
    ): EditExpenseUIEffect

    companion object {
        const val ERROR_COLOR = "Error"
        const val SUCCESS_COLOR = "Success"
    }
}