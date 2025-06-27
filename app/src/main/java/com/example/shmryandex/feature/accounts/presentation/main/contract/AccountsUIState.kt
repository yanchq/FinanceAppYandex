package com.example.shmryandex.feature.accounts.presentation.main.contract

import com.example.shmryandex.core.domain.entity.Account
import com.example.shmryandex.core.presentation.mvi.UIState

/**
 * Состояние UI для экрана счетов.
 * Содержит информацию о выбранном счете и список всех доступных счетов.
 */
data class AccountsUIState(
    val selectedAccount: Account? = null,
    val accounts: List<Account> = emptyList()
): UIState
