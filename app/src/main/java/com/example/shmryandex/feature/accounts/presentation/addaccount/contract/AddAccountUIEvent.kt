package com.example.shmryandex.feature.accounts.presentation.addaccount.contract

import com.example.shmryandex.core.presentation.mvi.UIEvent

sealed class AddAccountUIEvent: UIEvent {
    data class AddAccount(
        val name: String,
        val balance: String,
        val currency: String
    ): AddAccountUIEvent()
}