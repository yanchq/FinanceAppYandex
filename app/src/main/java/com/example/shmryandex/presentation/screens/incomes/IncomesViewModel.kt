package com.example.shmryandex.presentation.screens.incomes

import androidx.lifecycle.ViewModel
import com.example.shmryandex.domain.usecase.GetIncomesListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class IncomesViewModel @Inject constructor(
    private val getIncomesListUseCase: GetIncomesListUseCase
): ViewModel() {

    private val _uiState = MutableStateFlow(IncomesUiState())
    val uiState: StateFlow<IncomesUiState>
        get() = _uiState

    init {
        getIncomesList()
    }

    fun onIntent(intent: IncomesIntent) {
        when (intent) {
            is IncomesIntent.LoadIncomes -> {
                getIncomesList()
            }
        }
    }

    private fun getIncomesList() {
        val list = getIncomesListUseCase()
        var sum = 0
        list.forEach {
            sum += it.amount
        }
        _uiState.value = _uiState.value.copy(
            incomes = list,
            summary = sum
        )
    }
}