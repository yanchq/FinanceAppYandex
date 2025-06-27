package com.example.shmryandex.feature.accounts.presentation.main.contract

import com.example.shmryandex.core.domain.entity.Account
import com.example.shmryandex.core.presentation.mvi.UIEvent

sealed class AccountsUIEvent: UIEvent {
    data class SelectAccount(val account: Account): AccountsUIEvent()
}