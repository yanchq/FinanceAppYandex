package com.example.shmryandex.presentation.screens.account.addAccount

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shmryandex.data.network.Result
import com.example.shmryandex.domain.usecase.CreateAccountUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddAccountViewModel @Inject constructor(
    private val createAccountUseCase: CreateAccountUseCase
): ViewModel() {

    private val _uiState = MutableStateFlow<Boolean?>(null)
    val uiState: StateFlow<Boolean?>
        get() = _uiState

    fun onIntent(intent: AddAccountIntent) {
        when (intent) {
            is AddAccountIntent.AddAccount -> {
                viewModelScope.launch(Dispatchers.IO) {
                    when(createAccountUseCase(
                        name = intent.name,
                        balance = intent.balance,
                        currency = intent.currency
                    )) {
                        is Result.Error -> {
                            _uiState.value = false
                        }
                        Result.Loading -> {}
                        is Result.Success<*> -> {
                            _uiState.value = true
                        }
                    }
                }
            }
        }
    }
}