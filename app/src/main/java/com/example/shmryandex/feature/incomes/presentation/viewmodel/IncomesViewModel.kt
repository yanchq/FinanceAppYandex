package com.example.shmryandex.feature.incomes.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.shmryandex.core.data.network.model.Result
import com.example.shmryandex.core.domain.entity.Account
import com.example.shmryandex.core.domain.usecase.GetAccountsListUseCase
import com.example.shmryandex.core.presentation.mvi.BaseViewModel
import com.example.shmryandex.feature.incomes.domain.entity.Income
import com.example.shmryandex.feature.incomes.domain.usecase.GetIncomesListUseCase
import com.example.shmryandex.feature.incomes.presentation.contract.IncomesUIEffect
import com.example.shmryandex.feature.incomes.presentation.contract.IncomesUIEvent
import com.example.shmryandex.feature.incomes.presentation.contract.IncomesUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel для экрана доходов.
 * Управляет состоянием UI, обрабатывает пользовательские события
 * и координирует загрузку данных о доходах через use cases.
 */
@HiltViewModel
class IncomesViewModel @Inject constructor(
    private val getIncomesListUseCase: GetIncomesListUseCase,
    private val getAccountsListUseCase: GetAccountsListUseCase
): BaseViewModel<IncomesUIEvent, IncomesUIState, IncomesUIEffect>
    (IncomesUIState()) {

    override fun onEvent(event: IncomesUIEvent) {

    }

    init {
        getIncomesList()
    }

    private fun getIncomesList() {
        viewModelScope.launch(Dispatchers.IO) {
            val accounts = getAccountsListUseCase()
            when (val result = getIncomesListUseCase(accounts)) {
                is Result.Error -> {

                }
                Result.Loading -> {

                }
                is Result.Success<List<Income>> -> {
                    setState(currentState.copy(
                        incomes = result.data
                    ))
                }
            }
        }
    }
}