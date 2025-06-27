package com.example.shmryandex.feature.incomes.domain.usecase

import com.example.shmryandex.core.data.network.model.Result
import com.example.shmryandex.core.domain.entity.Account
import com.example.shmryandex.feature.incomes.domain.entity.Income
import com.example.shmryandex.feature.incomes.domain.repository.IncomesRepository
import javax.inject.Inject

class GetIncomesListUseCase @Inject constructor(
    private val repository: IncomesRepository
) {

    suspend operator fun invoke(accounts: List<Account>): Result<List<Income>> {
        return repository.getIncomesList(accounts)
    }
}