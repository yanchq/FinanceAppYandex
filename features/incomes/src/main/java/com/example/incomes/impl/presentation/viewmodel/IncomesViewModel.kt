package com.example.incomes.impl.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.core.data.network.model.Result
import com.example.core.domain.usecase.GetAccountsListUseCase
import com.example.core.presentation.mvi.BaseViewModel
import com.example.incomes.impl.domain.entity.Income
import com.example.incomes.impl.domain.usecase.GetIncomesListUseCase
import com.example.incomes.impl.presentation.contract.IncomesUIEffect
import com.example.incomes.impl.presentation.contract.IncomesUIEvent
import com.example.incomes.impl.presentation.contract.IncomesUIState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel для экрана доходов.
 * Управляет состоянием UI, обрабатывает пользовательские события
 * и координирует загрузку данных о доходах через use cases.
 */

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