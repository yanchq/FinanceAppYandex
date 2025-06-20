package com.example.shmryandex.domain.usecase

import com.example.shmryandex.data.network.Result
import com.example.shmryandex.domain.FinanceRepository
import com.example.shmryandex.domain.entity.Expense
import com.example.shmryandex.domain.entity.Income
import javax.inject.Inject

class LoadIncomesByPeriod @Inject constructor(
    private val repository: FinanceRepository
) {

    suspend operator fun invoke(
        startDate: String,
        endDate: String
    ): Result<List<Income>> {
        return repository.loadIncomesByPeriod(
            startDate = startDate,
            endDate = endDate
        )
    }
}