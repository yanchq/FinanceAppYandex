package com.example.shmryandex.presentation.screens.incomes.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shmryandex.data.network.Result
import com.example.shmryandex.domain.FinanceRepository
import com.example.shmryandex.domain.entity.Expense
import com.example.shmryandex.domain.entity.Income
import com.example.shmryandex.domain.usecase.LoadIncomesByPeriodUseCase
import com.example.shmryandex.presentation.screens.expenses.history.ExpensesHistoryUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class IncomesHistoryViewModel @Inject constructor(
    private val loadIncomesByPeriodUseCase: LoadIncomesByPeriodUseCase
): ViewModel() {
    private val _uiState = MutableStateFlow(IncomesHistoryUiState())
    val uiState: StateFlow<IncomesHistoryUiState> = _uiState.asStateFlow()

    fun loadIncomesByPeriod(
        startDate: String,
        endDate: String
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = loadIncomesByPeriodUseCase (
                startDate = startDate,
                endDate = endDate
            )
            when(result) {
                is Result.Error -> {

                }
                Result.Loading -> {}
                is Result.Success<List<Income>> -> {
                    _uiState.value = uiState.value.copy(
                        incomes = result.data
                    )
                }
            }
        }

    }
}