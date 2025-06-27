package com.example.shmryandex.feature.expenses.domain.repository

import com.example.shmryandex.core.data.network.model.Result
import com.example.shmryandex.core.domain.entity.Account
import com.example.shmryandex.feature.expenses.domain.entity.Expense

interface ExpensesRepository {

    suspend fun getExpensesList(accounts: List<Account>): Result<List<Expense>>
}