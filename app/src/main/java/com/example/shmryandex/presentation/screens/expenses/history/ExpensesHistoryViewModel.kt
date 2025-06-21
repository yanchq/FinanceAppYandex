package com.example.shmryandex.presentation.screens.expenses.history

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shmryandex.data.network.Result
import com.example.shmryandex.domain.entity.Expense
import com.example.shmryandex.domain.usecase.LoadExpensesByPeriodUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExpensesHistoryViewModel @Inject constructor(
    private val loadExpensesByPeriodUseCase: LoadExpensesByPeriodUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(ExpensesHistoryUiState())
    val uiState: StateFlow<ExpensesHistoryUiState> = _uiState.asStateFlow()

    fun loadExpensesByPeriod(
        startDate: String,
        endDate: String
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = loadExpensesByPeriodUseCase(
                startDate = startDate,
                endDate = endDate
            )
            when(result) {
                is Result.Error -> {

                }
                Result.Loading -> {}
                is Result.Success<List<Expense>> -> {
                    var totalAmount = 0.0
                    result.data.forEach {
                        totalAmount += it.amount
                    }
                    _uiState.value = uiState.value.copy(
                        expenses = result.data,
                        totalAmount = totalAmount
                    )
                }
            }
        }

    }
}

