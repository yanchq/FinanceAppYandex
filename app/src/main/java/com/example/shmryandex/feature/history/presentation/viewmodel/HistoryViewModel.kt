package com.example.shmryandex.feature.history.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.shmryandex.core.data.network.model.Result
import com.example.shmryandex.core.domain.usecase.GetAccountsListUseCase
import com.example.shmryandex.core.presentation.mvi.BaseViewModel
import com.example.shmryandex.feature.history.domain.entity.HistoryItem
import com.example.shmryandex.feature.history.domain.usecase.GetHistoryByPeriodUseCase
import com.example.shmryandex.feature.history.presentation.contract.HistoryUIEffect
import com.example.shmryandex.feature.history.presentation.contract.HistoryUIEvent
import com.example.shmryandex.feature.history.presentation.contract.HistoryUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getHistoryByPeriodUseCase: GetHistoryByPeriodUseCase,
    private val getAccountsListUseCase: GetAccountsListUseCase
) : BaseViewModel<HistoryUIEvent, HistoryUIState, HistoryUIEffect>
    (HistoryUIState()) {

    private val isIncome: Boolean by lazy {
        savedStateHandle.get<Boolean>("isIncome")!!
    }

    init {
        getHistory()
    }

    override fun onEvent(event: HistoryUIEvent) {
        when (event) {
            is HistoryUIEvent.EndDateSelected -> {
                setState(
                    currentState.copy(
                        endDate = event.endDate
                    )
                )
                getHistory()
            }

            is HistoryUIEvent.StartDateSelected -> {
                setState(
                    currentState.copy(
                        startDate = event.startDate
                    )
                )
                getHistory()
            }
        }
    }

    private fun getHistory() {
        viewModelScope.launch(Dispatchers.IO) {
            val accounts = getAccountsListUseCase()
            when (val historyListResult = getHistoryByPeriodUseCase(
                accounts = accounts,
                startDate = currentState.startDate,
                endDate = currentState.endDate,
                isIncome = isIncome
            )) {
                is Result.Error -> {

                }

                Result.Loading -> {

                }

                is Result.Success<List<HistoryItem>> -> {
                    setState(
                        currentState.copy(
                            items = historyListResult.data
                        )
                    )
                }
            }
        }
    }
}