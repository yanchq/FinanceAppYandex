package com.example.shmryandex.presentation.screens.account

import android.icu.util.Currency
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shmryandex.data.network.Result
import com.example.shmryandex.domain.entity.Account
import com.example.shmryandex.domain.usecase.CreateAccountUseCase
import com.example.shmryandex.domain.usecase.GetAccountUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(
    private val getAccountUseCase: GetAccountUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(AccountUiState())
    val uiState: StateFlow<AccountUiState> = _uiState.asStateFlow()

    init {
        loadAccount()
    }

    fun onIntent(intent: AccountIntent) {
        when (intent) {
            is AccountIntent.RefreshAccount -> loadAccount()
            is AccountIntent.SelectAccount -> {
                _uiState.value = uiState.value.copy(
                    selectedAccount = intent.account
                )
            }
        }
    }

    fun loadAccount(): StateFlow<List<Account>> {
        return getAccountUseCase()
    }
}