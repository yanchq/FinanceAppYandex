package com.example.accounts.impl.presentation.editaccount.contract

import com.example.core.presentation.mvi.UIEffect

sealed class EditAccountUIEffect: UIEffect {

    data class ShowSuccessSnackbar(
        val message: String = "Счет успешно изменен",
        val color: String = SUCCESS_COLOR
    ): EditAccountUIEffect()

    data class ShowErrorSnackbar(
        val message: String = "Произошла ошибка",
        val color: String = ERROR_COLOR
    ): EditAccountUIEffect()

    companion object {
        const val ERROR_COLOR = "Error"
        const val SUCCESS_COLOR = "Success"
    }
}