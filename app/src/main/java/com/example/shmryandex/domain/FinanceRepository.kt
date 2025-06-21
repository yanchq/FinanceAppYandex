package com.example.shmryandex.domain

import com.example.shmryandex.data.network.Result
import com.example.shmryandex.domain.entity.Account
import com.example.shmryandex.domain.entity.Category
import com.example.shmryandex.domain.entity.Expense
import com.example.shmryandex.domain.entity.Income
import kotlinx.coroutines.flow.StateFlow

interface FinanceRepository {

    fun getExpensesList(): List<Expense>

    fun getIncomesList(): List<Income>

    suspend fun loadTodayTransactions(): Result<Unit>

    suspend fun loadExpensesByPeriod(
        startDate: String,
        endDate: String
    ): Result<List<Expense>>

    suspend fun loadIncomesByPeriod(
        startDate: String,
        endDate: String
    ): Result<List<Income>>

    fun getAccountsList(): StateFlow<List<Account>>

    suspend fun loadAccountsList(): Result<Unit>

    suspend fun createAccount(
        name: String,
        balance: String,
        currency: String
    ): Result<Unit>

    suspend fun deleteAccount(id: Int): Result<Unit>

    suspend fun getCategoriesList(): Result<List<Category>>
}