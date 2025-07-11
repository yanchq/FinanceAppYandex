package com.example.expenses.impl.presentation.addexpense.contract

import com.example.core.domain.entity.Account
import com.example.core.domain.entity.Category
import com.example.core.presentation.mvi.UIState
import com.example.core.utils.getLocalTime
import java.time.LocalDate

data class AddExpenseUIState(
    val account: Account? = null,
    val category: Category? = null,
    val amount: String = "",
    val transactionDate: String = LocalDate.now().toString(),
    val transactionTime: String = getLocalTime(),
    val comment: String = "",
    val categoriesList: List<Category> = emptyList<Category>(),
    val accountsList: List<Account> = emptyList<Account>()
    ): UIState