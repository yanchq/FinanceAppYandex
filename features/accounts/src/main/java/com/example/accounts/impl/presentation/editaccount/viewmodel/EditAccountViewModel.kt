package com.example.accounts.impl.presentation.editaccount.viewmodel

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.core.data.network.model.Result
import com.example.core.domain.usecase.GetSelectedAccountUseCase
import com.example.core.domain.usecase.LoadAccountsUseCase
import com.example.core.presentation.mvi.BaseViewModel
import com.example.core.utils.ui.currencyOptions
import com.example.accounts.impl.domain.usecase.EditAccountUseCase
import com.example.accounts.impl.presentation.editaccount.contract.EditAccountUIEffect
import com.example.accounts.impl.presentation.editaccount.contract.EditAccountUIEvent
import com.example.accounts.impl.presentation.editaccount.contract.EditAccountUIState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class EditAccountViewModel @Inject constructor(
    private val editAccountUseCase: EditAccountUseCase,
    private val loadAccountsUseCase: LoadAccountsUseCase,
    private val getSelectedAccountUseCase: GetSelectedAccountUseCase
) : BaseViewModel<EditAccountUIEvent, EditAccountUIState, EditAccountUIEffect>
    (EditAccountUIState()) {

    init {
        initialData()
        Log.d("ScopesTest", "ViewModelCreated")
    }

    override fun onEvent(event: EditAccountUIEvent) {
        when (event) {
            is EditAccountUIEvent.EditAccount -> {
                editAccount()
            }

            is EditAccountUIEvent.BalanceEdited -> {
                setState(currentState.copy(balance = event.balance))
            }

            is EditAccountUIEvent.CurrencyEdited -> {
                setState(currentState.copy(currency = event.currency))
            }

            is EditAccountUIEvent.NameEdited -> {
                setState(currentState.copy(name = event.name))
            }
        }
    }

    private fun editAccount() {
        viewModelScope.launch(Dispatchers.IO) {
            when (editAccountUseCase(
                accountId = currentState.id,
                name = currentState.name,
                balance = currentState.balance,
                currency = currentState.currency!!.code
            )) {
                is Result.Error -> {
                    setEffect(EditAccountUIEffect.ShowErrorSnackbar())
                }

                Result.Loading -> {

                }

                is Result.Success<Unit> -> {
                    loadAccountsUseCase()
                    setEffect(EditAccountUIEffect.ShowSuccessSnackbar())
                }
            }
        }
    }

    private fun initialData() {
        viewModelScope.launch(Dispatchers.IO) {
            val account =
                getSelectedAccountUseCase() ?: throw RuntimeException("Selected account is null")
            val currency = currencyOptions.find { it.symbol == account.currency }
            setState(
                currentState.copy(
                    id = account.id,
                    name = account.name,
                    balance = account.balance.toString(),
                    currency = currency
                )
            )
        }

    }
}