package com.example.shmryandex.app.presentation.splash.viewmodel

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.core.data.network.model.Result
import com.example.core.domain.usecase.GetCategoriesListUseCase
import com.example.core.domain.usecase.LoadAccountsUseCase
import com.example.core.domain.usecase.SyncTransactionsUseCase
import com.example.core.presentation.mvi.BaseViewModel
import com.example.shmryandex.app.presentation.splash.contract.SplashUIEffect
import com.example.shmryandex.app.presentation.splash.contract.SplashUIEvent
import com.example.shmryandex.app.presentation.splash.contract.SplashUIState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel для сплеш-экрана.
 * Отвечает за загрузку начальных данных приложения и управление
 * навигацией после успешной инициализации.
 */

class SplashViewModel @Inject constructor(
    private val loadAccountsUseCase: LoadAccountsUseCase,
    private val getCategoriesListUseCase: GetCategoriesListUseCase,
    private val syncTransactionsUseCase: SyncTransactionsUseCase
) : BaseViewModel<SplashUIEvent, SplashUIState, SplashUIEffect>(
    SplashUIState()
) {
    override fun onEvent(event: SplashUIEvent) {

    }

    init {
        loadInitialData()
    }

    private fun loadInitialData() {
        viewModelScope.launch(Dispatchers.IO) {
            when (val accountsResult = loadAccountsUseCase()) {
                is Result.Success -> {
                    getCategoriesListUseCase()
                    val syncResult = syncTransactionsUseCase()
                    Log.d("SplashViewModel", "$syncResult")
                    setEffect(SplashUIEffect.NavigateToMainScreen)
                }

                is Result.Error -> {
                    Log.e(
                        "SplashViewModel",
                        "Failed to load accounts: ${accountsResult.exception.message}"
                    )
                }

                is Result.Loading -> {
                }
            }
        }
    }
}