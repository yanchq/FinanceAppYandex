package com.example.expenses.impl.domain.repository

import com.example.core.data.network.model.Result
import com.example.core.domain.entity.Account
import com.example.expenses.impl.domain.entity.Expense

/**
 * Репозиторий для работы с расходами.
 * Определяет методы для получения списка расходных операций
 * по заданному списку счетов.
 */
interface ExpensesRepository {

    suspend fun getExpensesList(accounts: List<Account>): Result<List<Expense>>
}