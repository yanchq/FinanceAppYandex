package com.example.shmryandex.domain

import com.example.shmryandex.data.network.Result
import com.example.shmryandex.domain.entity.Account
import com.example.shmryandex.domain.entity.Category
import com.example.shmryandex.domain.entity.Expense
import com.example.shmryandex.domain.entity.Income

interface FinanceRepository {

    suspend fun getExpensesList(): Result<List<Expense>>

    suspend fun getIncomesList(): Result<List<Income>>

    fun getAccountsList(): List<Account>

    suspend fun loadAccountsList(): Result<Unit>

    suspend fun getCategoriesList(): Result<List<Category>>
}