package com.example.shmryandex.feature.accounts.presentation.main.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.shmryandex.core.domain.usecase.GetAccountsFlowUseCase
import com.example.shmryandex.core.presentation.mvi.BaseViewModel
import com.example.shmryandex.feature.accounts.presentation.main.contract.AccountsUIEffect
import com.example.shmryandex.feature.accounts.presentation.main.contract.AccountsUIEvent
import com.example.shmryandex.feature.accounts.presentation.main.contract.AccountsUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel для главного экрана счетов.
 * Отвечает за управление списком счетов, обработку выбора счета пользователем
 * и поддержание актуального состояния через Flow.
 */
@HiltViewModel
class AccountsViewModel @Inject constructor(
    private val getAccountsFlowUseCase: GetAccountsFlowUseCase
) : BaseViewModel<AccountsUIEvent, AccountsUIState, AccountsUIEffect>
    (AccountsUIState()) {

    init {
        getAccounts()
    }

    override fun onEvent(event: AccountsUIEvent) {
        when (event) {
            is AccountsUIEvent.SelectAccount -> {
                setState(currentState.copy(
                    selectedAccount = event.account
                ))
            }
        }
    }

    private fun getAccounts() {
        viewModelScope.launch(Dispatchers.IO) {
            getAccountsFlowUseCase().collect { accounts ->
                setState(
                    currentState.copy(
                        accounts = accounts
                    )
                )
            }
        }
    }
}