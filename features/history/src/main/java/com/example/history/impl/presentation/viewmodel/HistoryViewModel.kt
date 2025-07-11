package com.example.history.impl.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.core.data.network.model.Result
import com.example.core.domain.usecase.GetAccountsListUseCase
import com.example.core.presentation.mvi.BaseViewModel
import com.example.history.impl.domain.entity.HistoryItem
import com.example.history.impl.domain.usecase.GetHistoryByPeriodUseCase
import com.example.history.impl.presentation.contract.HistoryUIEffect
import com.example.history.impl.presentation.contract.HistoryUIEvent
import com.example.history.impl.presentation.contract.HistoryUIState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel для экрана истории транзакций.
 * Управляет состоянием UI, обрабатывает события и загружает данные о транзакциях.
 * Использует MVI архитектуру через наследование от [BaseViewModel].
 *
 * @property getHistoryByPeriodUseCase Use case для получения истории транзакций
 * @property getAccountsListUseCase Use case для получения списка счетов
 * @property isIncome Флаг, определяющий тип отображаемых транзакций (доходы/расходы)
 */

class HistoryViewModel @Inject constructor(
    private val getHistoryByPeriodUseCase: GetHistoryByPeriodUseCase,
    private val getAccountsListUseCase: GetAccountsListUseCase
) : BaseViewModel<HistoryUIEvent, HistoryUIState, HistoryUIEffect>
    (HistoryUIState()) {

    /**
     * Обрабатывает события UI.
     * При изменении дат периода обновляет состояние и перезагружает историю транзакций.
     *
     * @param event Событие UI для обработки
     */
    override fun onEvent(event: HistoryUIEvent) {
        when (event) {
            is HistoryUIEvent.EndDateSelected -> {
                setState(
                    currentState.copy(
                        endDate = event.endDate
                    )
                )
                getHistory(event.isIncome)
            }

            is HistoryUIEvent.StartDateSelected -> {
                setState(
                    currentState.copy(
                        startDate = event.startDate
                    )
                )
                getHistory(event.isIncome)
            }
        }
    }

    /**
     * Загружает историю транзакций за выбранный период.
     * Получает список счетов и запрашивает транзакции для каждого счета.
     * Обновляет состояние UI при успешной загрузке данных.
     */
    private fun getHistory(isIncome: Boolean) {
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