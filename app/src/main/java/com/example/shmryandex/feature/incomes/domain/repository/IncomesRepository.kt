package com.example.shmryandex.feature.incomes.domain.repository

import com.example.shmryandex.core.data.network.model.Result
import com.example.shmryandex.core.domain.entity.Account
import com.example.shmryandex.feature.incomes.domain.entity.Income

/**
 * Репозиторий для работы с доходами.
 * Определяет методы для получения списка доходов по указанным счетам.
 */
interface IncomesRepository {

    suspend fun getIncomesList(accounts: List<Account>): Result<List<Income>>
}