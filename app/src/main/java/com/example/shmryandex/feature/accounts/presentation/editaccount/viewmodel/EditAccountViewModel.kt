package com.example.shmryandex.feature.accounts.presentation.editaccount.viewmodel

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.shmryandex.core.data.network.model.Result
import com.example.shmryandex.core.domain.entity.Account
import com.example.shmryandex.core.domain.usecase.LoadAccountsUseCase
import com.example.shmryandex.core.presentation.mvi.BaseViewModel
import com.example.shmryandex.core.presentation.ui.currencyOptions
import com.example.shmryandex.feature.accounts.domain.usecase.EditAccountUseCase
import com.example.shmryandex.feature.accounts.presentation.editaccount.contract.EditAccountUIEffect
import com.example.shmryandex.feature.accounts.presentation.editaccount.contract.EditAccountUIEvent
import com.example.shmryandex.feature.accounts.presentation.editaccount.contract.EditAccountUIState
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.URLDecoder
import javax.inject.Inject

@HiltViewModel
class EditAccountViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val editAccountUseCase: EditAccountUseCase,
    private val loadAccountsUseCase: LoadAccountsUseCase
) : BaseViewModel<EditAccountUIEvent, EditAccountUIState, EditAccountUIEffect>
    (EditAccountUIState()) {

    private val account: Account by lazy {
        val encoded = savedStateHandle.get<String>("account")!!
        val accountJson = URLDecoder.decode(encoded, "UTF-8")
        Gson().fromJson(accountJson, Account::class.java)
    }

    init {
        val currency = currencyOptions.find { it.symbol == account.currency }
        Log.d("EditedAccountTest", account.toString())
        Log.d("EditedAccountTest", currency.toString())
        setState(currentState.copy(
            name = account.name,
            balance = account.balance.toString(),
            currency = currency
        ))
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
                accountId = account.id,
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
                    setEffect(EditAccountUIEffect.ShowSuccessSnackbar())
                    loadAccountsUseCase()
                }
            }
        }
    }
}