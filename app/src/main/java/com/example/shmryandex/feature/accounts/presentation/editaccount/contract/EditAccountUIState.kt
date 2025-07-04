package com.example.shmryandex.feature.accounts.presentation.editaccount.contract

import com.example.shmryandex.core.presentation.mvi.UIState
import com.example.shmryandex.core.presentation.ui.CurrencyOption

data class EditAccountUIState(
    val id: Int = 0,
    val name: String = "",
    val balance: String = "",
    val currency: CurrencyOption? = null
): UIState
