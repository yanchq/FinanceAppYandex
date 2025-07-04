package com.example.shmryandex.feature.accounts.presentation.main.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.shmryandex.core.domain.entity.Account
import com.example.shmryandex.core.domain.usecase.GetAccountsFlowUseCase
import com.example.shmryandex.core.domain.usecase.GetSelectedAccountFlowUseCase
import com.example.shmryandex.core.domain.usecase.LoadAccountsUseCase
import com.example.shmryandex.core.domain.usecase.SetSelectedAccountUseCase
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
    private val getAccountsFlowUseCase: GetAccountsFlowUseCase,
    private val setSelectedAccountUseCase: SetSelectedAccountUseCase,
    private val getSelectedAccountFlowUseCase: GetSelectedAccountFlowUseCase,
) : BaseViewModel<AccountsUIEvent, AccountsUIState, AccountsUIEffect>
    (AccountsUIState()) {

    init {
        getAccounts()
        getSelectedAccount()
    }

    override fun onEvent(event: AccountsUIEvent) {
        when (event) {
            is AccountsUIEvent.SelectAccount -> {
                selectAccount(event.account)
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

    private fun getSelectedAccount() {
        viewModelScope.launch(Dispatchers.IO) {
            getSelectedAccountFlowUseCase().collect { account ->
                setState(currentState.copy(
                    selectedAccount = account
                ))
            }
        }
    }

    private fun selectAccount(account: Account) {
        setState(currentState.copy(
            selectedAccount = account
        ))
        setSelectedAccountUseCase(account)
    }
}