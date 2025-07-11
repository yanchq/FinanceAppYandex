package com.example.incomes.impl.domain.usecase

import com.example.core.data.network.model.Result
import com.example.core.domain.entity.Account
import com.example.incomes.impl.domain.entity.Income
import com.example.incomes.impl.domain.repository.IncomesRepository
import javax.inject.Inject

/**
 * Use case для получения списка доходов.
 * Принимает список счетов для фильтрации.
 * Возвращает Result с списком доходов в случае успеха или ошибку в случае неудачи.
 */
class GetIncomesListUseCase @Inject constructor(
    private val repository: IncomesRepository
) {

    suspend operator fun invoke(accounts: List<Account>): Result<List<Income>> {
        return repository.getIncomesList(accounts)
    }
}