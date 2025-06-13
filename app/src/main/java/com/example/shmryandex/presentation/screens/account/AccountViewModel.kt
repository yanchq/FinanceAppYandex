package com.example.shmryandex.presentation.screens.account

import androidx.lifecycle.ViewModel
import com.example.shmryandex.domain.entity.Account
import com.example.shmryandex.domain.usecase.GetAccountUseCase
import com.example.shmryandex.presentation.screens.expenses.ExpensesUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(
    private val getAccountUseCase: GetAccountUseCase
): ViewModel() {

    private val _uiState: MutableStateFlow<Account?> = MutableStateFlow(null)
    val uiState: StateFlow<Account?>
        get() = _uiState

    init {
        getAccount()
    }

    fun onIntent(intent: AccountIntent) {
        when (intent) {
            is AccountIntent.GetAccount -> {
                getAccount()
            }
        }
    }

    private fun getAccount() {
        val account = getAccountUseCase()
        _uiState.value = account
    }
}