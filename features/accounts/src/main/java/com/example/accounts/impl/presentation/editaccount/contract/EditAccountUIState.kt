package com.example.accounts.impl.presentation.editaccount.contract

import com.example.core.presentation.mvi.UIState
import com.example.core.utils.ui.CurrencyOption

data class EditAccountUIState(
    val id: Int = 0,
    val name: String = "",
    val balance: String = "",
    val currency: CurrencyOption? = null
): UIState
