package com.example.shmryandex.presentation.screens.expenses

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shmryandex.data.network.Result
import com.example.shmryandex.domain.entity.Expense
import com.example.shmryandex.domain.usecase.GetExpensesListUseCase
import com.example.shmryandex.presentation.screens.expenses.history.ExpensesHistoryUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExpensesViewModel @Inject constructor(
    private val getExpensesListUseCase: GetExpensesListUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(ExpensesHistoryUiState())
    val uiState: StateFlow<ExpensesHistoryUiState> = _uiState.asStateFlow()

    init {
        loadExpenses()
    }

    fun onIntent(intent: ExpensesIntent) {
        when (intent) {
            ExpensesIntent.RefreshExpenses -> loadExpenses()
        }
    }

    private fun loadExpenses() {
        val expensesList = getExpensesListUseCase()
        _uiState.value = _uiState.value.copy(
            expenses = expensesList
        )
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("ExpensesViewModelTest", "Cleared")
    }
}