package com.example.shmryandex.feature.incomes.domain.repository

import com.example.shmryandex.core.data.network.model.Result
import com.example.shmryandex.core.domain.entity.Account
import com.example.shmryandex.feature.incomes.domain.entity.Income

interface IncomesRepository {

    suspend fun getIncomesList(accounts: List<Account>): Result<List<Income>>
}