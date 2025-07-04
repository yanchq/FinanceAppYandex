package com.example.shmryandex.feature.accounts.presentation.editaccount.contract

import com.example.shmryandex.core.presentation.mvi.UIEvent
import com.example.shmryandex.core.presentation.ui.CurrencyOption

sealed class EditAccountUIEvent: UIEvent {

    data class EditAccount(
        val name: String,
        val balance: String,
        val currency: String
    ): EditAccountUIEvent()

    data class NameEdited(val name: String): EditAccountUIEvent()

    data class BalanceEdited(val balance: String): EditAccountUIEvent()

    data class CurrencyEdited(val currency: CurrencyOption): EditAccountUIEvent()
}