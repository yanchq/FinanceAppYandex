package com.example.shmryandex.feature.accounts.presentation.main.contract

import com.example.shmryandex.core.domain.entity.Account
import com.example.shmryandex.core.presentation.mvi.UIEvent

/**
 * Sealed класс, определяющий события пользовательского интерфейса для экрана счетов.
 * Содержит события, связанные с взаимодействием пользователя со списком счетов,
 * такие как выбор конкретного счета.
 */
sealed class AccountsUIEvent: UIEvent {
    data class SelectAccount(val account: Account): AccountsUIEvent()
}