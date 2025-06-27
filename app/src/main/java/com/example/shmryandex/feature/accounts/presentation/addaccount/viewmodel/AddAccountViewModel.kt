package com.example.shmryandex.feature.accounts.presentation.addaccount.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.shmryandex.core.data.network.model.Result
import com.example.shmryandex.core.domain.usecase.LoadAccountsUseCase
import com.example.shmryandex.core.presentation.mvi.BaseViewModel
import com.example.shmryandex.feature.accounts.domain.usecase.CreateAccountUseCase
import com.example.shmryandex.feature.accounts.presentation.addaccount.contract.AddAccountUIEffect
import com.example.shmryandex.feature.accounts.presentation.addaccount.contract.AddAccountUIEvent
import com.example.shmryandex.feature.accounts.presentation.addaccount.contract.AddAccountUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel для экрана добавления нового счета.
 * Обрабатывает логику создания счета, включая валидацию данных
 * и отображение результатов операции пользователю.
 */
@HiltViewModel
class AddAccountViewModel @Inject constructor(
    private val createAccountUseCase: CreateAccountUseCase,
    private val loadAccountsUseCase: LoadAccountsUseCase
) : BaseViewModel<AddAccountUIEvent, AddAccountUIState, AddAccountUIEffect>
    (AddAccountUIState()) {

    override fun onEvent(event: AddAccountUIEvent) {
        when (event) {
            is AddAccountUIEvent.AddAccount -> {
                createAccount(
                    name = event.name,
                    balance = event.balance,
                    currency = event.currency
                )
            }
        }
    }

    private fun createAccount(
        name: String,
        balance: String,
        currency: String
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            when (createAccountUseCase(
                name = name,
                balance = balance,
                currency = currency
            )) {
                is Result.Error -> {
                    setEffect(AddAccountUIEffect.ShowErrorSnackbar())
                }

                Result.Loading -> {

                }

                is Result.Success<Unit> -> {
                    setEffect(AddAccountUIEffect.ShowSuccessSnackbar())
                    loadAccountsUseCase()
                }
            }
        }

    }
}