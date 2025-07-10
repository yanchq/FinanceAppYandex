package com.example.accounts.impl.presentation.editaccount.contract

import com.example.core.presentation.mvi.UIEvent
import com.example.core.utils.ui.CurrencyOption

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