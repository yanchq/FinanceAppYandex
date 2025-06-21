package com.example.shmryandex.domain.usecase

import com.example.shmryandex.data.network.Result
import com.example.shmryandex.domain.FinanceRepository
import com.example.shmryandex.domain.entity.Expense
import javax.inject.Inject

class LoadExpensesByPeriodUseCase @Inject constructor(
    private val repository: FinanceRepository
) {

    suspend operator fun invoke(
        startDate: String,
        endDate: String
    ): Result<List<Expense>> {
        return repository.loadExpensesByPeriod(
            startDate = startDate,
            endDate = endDate
        )
    }
}