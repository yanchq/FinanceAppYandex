package com.example.shmryandex.presentation.screens.incomes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shmryandex.data.network.Result
import com.example.shmryandex.domain.usecase.GetIncomesListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class IncomesViewModel @Inject constructor(
    private val getIncomesListUseCase: GetIncomesListUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<IncomesUiState>(IncomesUiState())
    val uiState: StateFlow<IncomesUiState> = _uiState.asStateFlow()

    init {
        loadIncomes()
    }

    fun onIntent(intent: IncomesIntent) {
        when (intent) {
            IncomesIntent.RefreshIncomes -> loadIncomes()
        }
    }

    private fun loadIncomes() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, error = null)
            
            when (val result = getIncomesListUseCase()) {
                is Result.Success -> {
                    _uiState.value = _uiState.value.copy(
                        incomes = result.data,
                        isLoading = false,
                        error = null
                    )
                }
                is Result.Error -> {
                    _uiState.value = _uiState.value.copy(
                        incomes = emptyList(),
                        isLoading = false,
                        error = result.exception.message
                    )
                }
                is Result.Loading -> {
                    _uiState.value = _uiState.value.copy(isLoading = true)
                }
            }
        }
    }
}