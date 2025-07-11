package com.example.expenses.impl.presentation.addexpense.viewmodel

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.core.data.network.model.Result
import com.example.core.domain.usecase.CreateTransactionUseCase
import com.example.core.domain.usecase.GetAccountsListUseCase
import com.example.core.domain.usecase.GetCategoriesListUseCase
import com.example.core.presentation.mvi.BaseViewModel
import com.example.core.utils.toUtcIsoString
import com.example.expenses.impl.presentation.addexpense.contract.AddExpenseUIEffect
import com.example.expenses.impl.presentation.addexpense.contract.AddExpenseUIEvent
import com.example.expenses.impl.presentation.addexpense.contract.AddExpenseUIState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class AddExpenseViewModel @Inject constructor(
    private val createTransactionUseCase: CreateTransactionUseCase,
    private val getAccountsListUseCase: GetAccountsListUseCase,
    private val getCategoriesListUseCase: GetCategoriesListUseCase
) : BaseViewModel<AddExpenseUIEvent, AddExpenseUIState, AddExpenseUIEffect>
    (AddExpenseUIState()) {

    init {
        loadInitialData()
    }

    override fun onEvent(event: AddExpenseUIEvent) {
        when (event) {
            AddExpenseUIEvent.AddExpenseEvent -> {
                createTransaction()
            }

            is AddExpenseUIEvent.AccountsSelected -> {
                setState(
                    currentState.copy(
                        account = event.account
                    )
                )
            }

            is AddExpenseUIEvent.AmountChanged -> {
                setState(
                    currentState.copy(
                        amount = event.amount
                    )
                )
            }

            is AddExpenseUIEvent.CategoryChanged -> {
                setState(
                    currentState.copy(
                        category = event.category
                    )
                )
            }

            is AddExpenseUIEvent.CommentChanged -> {
                setState(
                    currentState.copy(
                        comment = event.comment
                    )
                )
            }

            is AddExpenseUIEvent.DateChanged -> {
                setState(
                    currentState.copy(
                        transactionDate = event.date
                    )
                )
            }

            is AddExpenseUIEvent.TimeChanged -> {
                setState(
                    currentState.copy(
                        transactionTime = event.time
                    )
                )
            }
        }
    }

    private fun createTransaction() {
        viewModelScope.launch(Dispatchers.IO) {
            when (createTransactionUseCase(
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
                    setEffect(AddExpenseUIEffect.ShowErrorSnackbar())
                }

                Result.Loading -> {}
                is Result.Success<*> -> {
                    setEffect(AddExpenseUIEffect.ShowSuccessSnackbar())
                }
            }
        }
    }

    private fun loadInitialData() {
        viewModelScope.launch(Dispatchers.IO) {
            val accountsList = getAccountsListUseCase()
            val categoriesList = getCategoriesListUseCase()
            setState(
                currentState.copy(
                    accountsList = accountsList,
                    account = accountsList[0],
                    categoriesList = categoriesList,
                    category = categoriesList[0]
                )
            )
            Log.d("AddTransactionTest", currentState.toString())
        }
    }
}