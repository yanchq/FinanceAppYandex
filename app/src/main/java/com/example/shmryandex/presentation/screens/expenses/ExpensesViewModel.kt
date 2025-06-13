package com.example.shmryandex.presentation.screens.expenses

import androidx.lifecycle.ViewModel
import com.example.shmryandex.domain.usecase.GetExpensesListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class ExpensesViewModel @Inject constructor(
    private val getExpensesListUseCase: GetExpensesListUseCase
): ViewModel() {

    private val _uiState = MutableStateFlow(ExpensesUiState())
    val uiState: StateFlow<ExpensesUiState>
        get() = _uiState

    init {
        getExpensesList()
    }

    fun onIntent(intent: ExpensesIntent) {
        when (intent) {
            is ExpensesIntent.LoadExpenses -> {
                getExpensesList()
            }
        }
    }

    private fun getExpensesList() {
        val list = getExpensesListUseCase()
        var sum = 0
        list.forEach {
            sum += it.amount
        }
        _uiState.value = _uiState.value.copy(
            expenses = list,
            summary = sum)
    }
}