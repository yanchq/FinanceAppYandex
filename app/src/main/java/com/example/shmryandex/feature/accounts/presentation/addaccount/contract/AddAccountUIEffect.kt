package com.example.shmryandex.feature.accounts.presentation.addaccount.contract

import androidx.compose.ui.graphics.Color
import com.example.shmryandex.core.presentation.mvi.UIEffect
import com.example.shmryandex.core.presentation.ui.theme.PrimaryGreen

/**
 * Sealed класс для определения UI эффектов экрана добавления счета.
 * Включает эффекты для отображения уведомлений об успешном создании
 * счета или возникновении ошибки.
 */
sealed class AddAccountUIEffect: UIEffect {
    data class ShowSuccessSnackbar(
        val message: String = "Счет успешно добавлен",
        val color: String = SUCCESS_COLOR
    ): AddAccountUIEffect()

    data class ShowErrorSnackbar(
        val message: String = "Произошла ошибка",
        val color: String = ERROR_COLOR
    ): AddAccountUIEffect()

    companion object {
        const val ERROR_COLOR = "Error"
        const val SUCCESS_COLOR = "Success"
    }
}