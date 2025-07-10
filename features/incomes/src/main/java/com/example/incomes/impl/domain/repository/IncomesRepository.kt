package com.example.incomes.impl.domain.repository

import com.example.core.data.network.model.Result
import com.example.core.domain.entity.Account
import com.example.incomes.impl.domain.entity.Income

/**
 * Репозиторий для работы с доходами.
 * Определяет методы для получения списка доходов по указанным счетам.
 */
interface IncomesRepository {

    suspend fun getIncomesList(accounts: List<Account>): Result<List<Income>>
}