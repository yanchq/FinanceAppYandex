package com.example.shmryandex.domain.usecase

import com.example.shmryandex.data.network.Result
import com.example.shmryandex.domain.FinanceRepository
import com.example.shmryandex.domain.entity.Income
import javax.inject.Inject

class GetIncomesListUseCase @Inject constructor(
    private val repository: FinanceRepository
) {
    operator fun invoke(): List<Income> = repository.getIncomesList()
}