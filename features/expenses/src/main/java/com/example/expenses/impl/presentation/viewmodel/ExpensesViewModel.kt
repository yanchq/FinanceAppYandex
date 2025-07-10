package com.example.expenses.impl.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.core.data.network.model.Result
import com.example.core.domain.usecase.GetAccountsListUseCase
import com.example.core.presentation.mvi.BaseViewModel
import com.example.expenses.impl.domain.entity.Expense
import com.example.expenses.impl.domain.usecase.GetExpensesListUseCase
import com.example.expenses.impl.presentation.contract.ExpensesUIEffect
import com.example.expenses.impl.presentation.contract.ExpensesUIEvent
import com.example.expenses.impl.presentation.contract.ExpensesUIState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel для экрана расходов.
 * Управляет загрузкой и отображением списка расходов по всем счетам,
 * обрабатывает пользовательские события и обновляет UI состояние.
 */


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