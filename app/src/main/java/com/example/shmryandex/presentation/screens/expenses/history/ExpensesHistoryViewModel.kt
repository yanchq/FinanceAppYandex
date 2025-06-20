package com.example.shmryandex.presentation.screens.expenses.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shmryandex.domain.entity.Expense
import com.example.shmryandex.domain.usecase.GetExpensesListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class ExpensesHistoryViewModel @Inject constructor(
    private val getExpensesListUseCase: GetExpensesListUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(ExpensesHistoryUiState())
    val uiState: StateFlow<ExpensesHistoryUiState> = _uiState.asStateFlow()

    init {

    }


}

