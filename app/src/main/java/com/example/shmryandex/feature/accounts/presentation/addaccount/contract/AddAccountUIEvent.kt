package com.example.shmryandex.feature.accounts.presentation.addaccount.contract

import com.example.shmryandex.core.presentation.mvi.UIEvent

/**
 * Sealed класс для определения событий UI экрана добавления счета.
 * Содержит событие добавления нового счета с необходимыми параметрами:
 * названием, балансом и валютой.
 */
sealed class AddAccountUIEvent: UIEvent {
    data class AddAccount(
        val name: String,
        val balance: String,
        val currency: String
    ): AddAccountUIEvent()
}