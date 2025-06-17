package com.example.shmryandex.presentation.screens.splash

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shmryandex.data.network.Result
import com.example.shmryandex.domain.usecase.LoadAccountsListUseCase
import com.example.shmryandex.presentation.screens.account.AccountUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val loadAccountsListUseCase: LoadAccountsListUseCase
): ViewModel() {

    private val _uiState = MutableStateFlow(false)
    val uiState: StateFlow<Boolean> = _uiState.asStateFlow()

    init {
        loadAccounts()
    }

    private fun loadAccounts() {
        viewModelScope.launch {
            val result = loadAccountsListUseCase()
            Log.d("LoadAccountsTest", result.toString())
            when(result) {
                is Result.Success -> {
                    _uiState.value = true
                }
                is Result.Error -> {

                }
                is Result.Loading -> {

                }
            }

        }
    }
}