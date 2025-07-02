package com.example.shmryandex.feature.accounts.presentation.editaccount.contract

import com.example.shmryandex.core.presentation.mvi.UIEffect
import com.example.shmryandex.feature.accounts.presentation.addaccount.contract.AddAccountUIEffect

sealed class EditAccountUIEffect: UIEffect {

    data class ShowSuccessSnackbar(
        val message: String = "Счет успешно добавлен",
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