package com.example.history.impl.presentation.analytics.viewmodel

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.core.data.network.model.Result
import com.example.core.domain.usecase.GetAccountsListUseCase
import com.example.core.presentation.mvi.BaseViewModel
import com.example.core.presentation.mvi.ScreenContent
import com.example.history.impl.domain.entity.HistoryItem
import com.example.history.impl.domain.usecase.GetHistoryByPeriodUseCase
import com.example.history.impl.presentation.analytics.contract.AnalyticsUIEffect
import com.example.history.impl.presentation.analytics.contract.AnalyticsUIEvent
import com.example.history.impl.presentation.analytics.contract.AnalyticsUIState
import com.example.history.impl.presentation.analytics.contract.GroupedHistory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.math.BigDecimal
import java.math.RoundingMode
import javax.inject.Inject

class AnalyticsViewModel @Inject constructor(
    private val getHistoryByPeriodUseCase: GetHistoryByPeriodUseCase,
    private val getAccountsListUseCase: GetAccountsListUseCase
): BaseViewModel<AnalyticsUIEvent, AnalyticsUIState, AnalyticsUIEffect>
    (AnalyticsUIState()){

    override fun onEvent(event: AnalyticsUIEvent) {
        when (event) {
            is AnalyticsUIEvent.EndDateSelected -> {
                setState(
                    currentState.copy(
                        endDate = event.endDate
                    )
                )
                getHistory(event.isIncome)
            }
            is AnalyticsUIEvent.StartDateSelected -> {
                setState(
                    currentState.copy(
                        startDate = event.startDate
                    )
                )
                getHistory(event.isIncome)
            }
        }
    }

    private fun getHistory(isIncome: Boolean) {
        setState(currentState.copy(
            screenContent = ScreenContent.Loading,
            items = emptyList()
        ))
        viewModelScope.launch(Dispatchers.IO) {
            val accounts = getAccountsListUseCase()
            when (val historyListResult = getHistoryByPeriodUseCase(
                accounts = accounts,
                startDate = currentState.startDate,
                endDate = currentState.endDate,
                isIncome = isIncome
            )) {
                is Result.Error -> {
                    Log.d("LoadAnalytics", historyListResult.exception.toString())
                }

                Result.Loading -> {

                }

                is Result.Success<List<HistoryItem>> -> {
                    setState(
                        currentState.copy(
                            items =  groupAndCalculatePercentage(historyListResult.data),
                            screenContent = ScreenContent.Content
                        )
                    )
                }
            }
        }
    }

    private fun groupAndCalculatePercentage(items: List<HistoryItem>): List<GroupedHistory> {
        val totalAmount = items.sumOf { it.amount }

        return items
            .groupBy { it.name }
            .map { (name, group) ->
                val amountSum = group.sumOf { it.amount }
                val emoji = group.first().emoji
                val percentage = if (totalAmount != 0.0) {
                    BigDecimal((amountSum / totalAmount) * 100)
                        .setScale(2, RoundingMode.HALF_UP)
                        .toDouble()
                } else {
                    0.0
                }
                GroupedHistory(
                    name = name,
                    amount = amountSum,
                    emoji = emoji,
                    percentage = percentage
                )
            }
    }
}