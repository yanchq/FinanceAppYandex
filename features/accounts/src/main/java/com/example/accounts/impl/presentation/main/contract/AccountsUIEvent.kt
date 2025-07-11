package com.example.accounts.impl.presentation.main.contract

import com.example.core.domain.entity.Account
import com.example.core.presentation.mvi.UIEvent

/**
 * Sealed класс, определяющий события пользовательского интерфейса для экрана счетов.
 * Содержит события, связанные с взаимодействием пользователя со списком счетов,
 * такие как выбор конкретного счета.
 */
sealed class AccountsUIEvent: UIEvent {
    data class SelectAccount(val account: Account): AccountsUIEvent()
}