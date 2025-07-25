package com.example.accounts.impl.presentation.main.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.core.domain.entity.Account
import com.example.core.domain.usecase.GetAccountsFlowUseCase
import com.example.core.domain.usecase.GetSelectedAccountFlowUseCase
import com.example.core.domain.usecase.SetSelectedAccountUseCase
import com.example.core.presentation.mvi.BaseViewModel
import com.example.accounts.impl.presentation.main.contract.AccountsUIEffect
import com.example.accounts.impl.presentation.main.contract.AccountsUIEvent
import com.example.accounts.impl.presentation.main.contract.AccountsUIState
import com.example.core.data.network.model.Result
import com.example.core.domain.entity.Transaction
import com.example.core.domain.usecase.GetTransactionsByPeriodUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

/**
 * ViewModel для главного экрана счетов.
 * Отвечает за управление списком счетов, обработку выбора счета пользователем
 * и поддержание актуального состояния через Flow.
 */
class AccountsViewModel @Inject constructor(
    private val getAccountsFlowUseCase: GetAccountsFlowUseCase,
    private val setSelectedAccountUseCase: SetSelectedAccountUseCase,
    private val getSelectedAccountFlowUseCase: GetSelectedAccountFlowUseCase,
    private val getTransactionsByPeriodUseCase: GetTransactionsByPeriodUseCase
) : BaseViewModel<AccountsUIEvent, AccountsUIState, AccountsUIEffect>
    (AccountsUIState()) {

    init {
        getAccounts()
        getSelectedAccount()
    }

    override fun onEvent(event: AccountsUIEvent) {
        when (event) {
            is AccountsUIEvent.SelectAccount -> {
                selectAccount(event.account)
            }
        }
    }

    private fun getAccounts() {
        viewModelScope.launch(Dispatchers.IO) {
            getAccountsFlowUseCase().collect { accounts ->
                setState(
                    currentState.copy(
                        accounts = accounts
                    )
                )
                setMonthTransactionsGraphInfo(accounts)
            }
        }
    }

    private fun getSelectedAccount() {
        viewModelScope.launch(Dispatchers.IO) {
            getSelectedAccountFlowUseCase().collect { account ->
                setState(
                    currentState.copy(
                        selectedAccount = account
                    )
                )
            }
        }
    }

    private fun selectAccount(account: Account) {
        setState(
            currentState.copy(
                selectedAccount = account
            )
        )
        setSelectedAccountUseCase(account)
    }

    private suspend fun setMonthTransactionsGraphInfo(accounts: List<Account>) {
        val startDate = LocalDate.now().withDayOfMonth(1).toString()
        val endDate = LocalDate.now().toString()
        when (val transactions = getTransactionsByPeriodUseCase(
            accounts = accounts,
            startDate = startDate,
            endDate = endDate
        )) {
            is Result.Error -> {}
            Result.Loading -> {}
            is Result.Success<List<Transaction>> -> {
                val transactionsAmountSumByDays = getDailyTransactionSums(transactions.data)
                val currentMonth = LocalDate.now().monthValue
                setState(
                    currentState.copy(
                        graphInfo = Pair(currentMonth, transactionsAmountSumByDays)
                    )
                )
            }
        }
    }

    private fun getDailyTransactionSums(transactions: List<Transaction>): List<Double> {
        val today = LocalDate.now()
        val daysInMonth = today.lengthOfMonth()
        val dailySums = MutableList(daysInMonth) { 0.0 }

        for (transaction in transactions) {
            val day = transaction.transactionDate.substringAfterLast("-").toIntOrNull()
            if (day != null && day in 1..daysInMonth) {
                dailySums[day - 1] += transaction.amount
            }
        }
        return dailySums
    }
}