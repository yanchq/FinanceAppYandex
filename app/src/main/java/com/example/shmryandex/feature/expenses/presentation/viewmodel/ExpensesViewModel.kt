package com.example.shmryandex.feature.expenses.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.shmryandex.core.data.network.model.Result
import com.example.shmryandex.core.domain.usecase.GetAccountsListUseCase
import com.example.shmryandex.core.presentation.mvi.BaseViewModel
import com.example.shmryandex.feature.expenses.domain.entity.Expense
import com.example.shmryandex.feature.expenses.domain.usecase.GetExpensesListUseCase
import com.example.shmryandex.feature.expenses.presentation.contract.ExpensesUIEffect
import com.example.shmryandex.feature.expenses.presentation.contract.ExpensesUIEvent
import com.example.shmryandex.feature.expenses.presentation.contract.ExpensesUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel для экрана расходов.
 * Управляет загрузкой и отображением списка расходов по всем счетам,
 * обрабатывает пользовательские события и обновляет UI состояние.
 */

@HiltViewModel
class ExpensesViewModel @Inject constructor(
    private val getExpensesListUseCase: GetExpensesListUseCase,
    private val getAccountsListUseCase: GetAccountsListUseCase
): BaseViewModel<ExpensesUIEvent, ExpensesUIState, ExpensesUIEffect>
    (ExpensesUIState()) {

    override fun onEvent(event: ExpensesUIEvent) {
        when (event) {
            ExpensesUIEvent.LoadExpenses -> {
                Log.d("LoadExpensesTest", "Loaded")
                loadExpensesList()
            }
        }
    }

    private fun loadExpensesList() {
        viewModelScope.launch(Dispatchers.IO) {
            val accounts = getAccountsListUseCase()
            val result = getExpensesListUseCase(accounts)
            Log.d("LoadExpenses", result.toString())
            when (result) {
                is Result.Error -> {

                }
                Result.Loading -> {

                }
                is Result.Success<List<Expense>> -> {
                    setState(currentState.copy(
                        expenses = result.data
                    ))
                }
            }
        }
    }
}