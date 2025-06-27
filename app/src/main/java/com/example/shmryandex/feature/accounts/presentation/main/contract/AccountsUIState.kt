package com.example.shmryandex.feature.accounts.presentation.main.contract

import com.example.shmryandex.core.domain.entity.Account
import com.example.shmryandex.core.presentation.mvi.UIState

data class AccountsUIState(
    val selectedAccount: Account? = null,
    val accounts: List<Account> = emptyList()
): UIState
