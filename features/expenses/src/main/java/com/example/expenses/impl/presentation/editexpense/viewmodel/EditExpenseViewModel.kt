package com.example.expenses.impl.presentation.editexpense.viewmodel

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.core.data.network.model.Result
import com.example.core.domain.entity.DetailedTransaction
import com.example.core.domain.usecase.CreateTransactionUseCase
import com.example.core.domain.usecase.EditTransactionUseCase
import com.example.core.domain.usecase.GetAccountsListUseCase
import com.example.core.domain.usecase.GetCategoriesListUseCase
import com.example.core.domain.usecase.GetDetailedTransactionUseCase
import com.example.core.presentation.mvi.BaseViewModel
import com.example.core.utils.toUtcIsoString
import com.example.expenses.impl.presentation.editexpense.contract.EditExpenseUIEffect
import com.example.expenses.impl.presentation.editexpense.contract.EditExpenseUIEvent
import com.example.expenses.impl.presentation.editexpense.contract.EditExpenseUIState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class EditExpenseViewModel @Inject constructor(
    private val editTransactionUseCase: EditTransactionUseCase,
    private val getAccountsListUseCase: GetAccountsListUseCase,
    private val getCategoriesListUseCase: GetCategoriesListUseCase,
    private val getDetailedTransactionUseCase: GetDetailedTransactionUseCase
) : BaseViewModel<EditExpenseUIEvent, EditExpenseUIState, EditExpenseUIEffect>
    (EditExpenseUIState()) {

    init {
        loadInitialData()
    }

    override fun onEvent(event: EditExpenseUIEvent) {
        when (event) {
            EditExpenseUIEvent.AddExpenseEvent -> {
                editTransaction()
            }

            is EditExpenseUIEvent.AccountsSelected -> {
                setState(
                    currentState.copy(
                        account = event.account
                    )
                )
            }

            is EditExpenseUIEvent.AmountChanged -> {
                setState(
                    currentState.copy(
                        amount = event.amount
                    )
                )
            }

            is EditExpenseUIEvent.CategoryChanged -> {
                setState(
                    currentState.copy(
                        category = event.category
                    )
                )
            }

            is EditExpenseUIEvent.CommentChanged -> {
                setState(
                    currentState.copy(
                        comment = event.comment
                    )
                )
            }

            is EditExpenseUIEvent.DateChanged -> {
                setState(
                    currentState.copy(
                        transactionDate = event.date
                    )
                )
            }

            is EditExpenseUIEvent.TimeChanged -> {
                setState(
                    currentState.copy(
                        transactionTime = event.time
                    )
                )
            }

            is EditExpenseUIEvent.TransactionInit -> {
                viewModelScope.launch(Dispatchers.IO) {
                    val categoriesList = getCategoriesListUseCase()

                    when (val transaction = getDetailedTransactionUseCase(event.transactionId)) {
                        is Result.Error -> {

                        }
                        Result.Loading -> {

                        }
                        is Result.Success<DetailedTransaction> -> {
                            val filteredCategories = categoriesList.filter { category ->
                                category.isIncome == transaction.data.category.isIncome
                            }
                            Log.d("InitDataTest", transaction.data.toString())
                            setState(currentState.copy(
                                id = transaction.data.id.toInt(),
                                categoriesList = filteredCategories,
                                category = transaction.data.category,
                                amount = transaction.data.amount.toString(),
                                account = transaction.data.account,
                                transactionDate = transaction.data.transactionDate,
                                transactionTime = transaction.data.transactionTime,
                                comment = transaction.data.comment
                            ))
                        }
                    }
                }
            }
        }
    }

    private fun editTransaction() {
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = editTransactionUseCase(
                transactionId = currentState.id,
                accountId = currentState.account!!.id,
                categoryId = currentState.category!!.id.toInt(),
                amount = currentState.amount,
                transactionDate = toUtcIsoString(
                    dateString = currentState.transactionDate,
                    timeString = currentState.transactionTime
                ),
                comment = currentState.comment
            )) {
                is Result.Error -> {
                    setEffect(EditExpenseUIEffect.ShowErrorSnackbar())
                    Log.d("EditErrorLog", result.exception.toString())
                }

                Result.Loading -> {}
                is Result.Success<*> -> {
                    setEffect(EditExpenseUIEffect.ShowSuccessSnackbar())
                }
            }
        }
    }

    private fun loadInitialData() {
        viewModelScope.launch {
            val accountsList = getAccountsListUseCase()
            setState(
                currentState.copy(
                    accountsList = accountsList,
                )
            )
            Log.d("AddTransactionTest", currentState.toString())
        }
    }
}