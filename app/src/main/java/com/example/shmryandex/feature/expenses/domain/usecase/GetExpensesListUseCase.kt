package com.example.shmryandex.feature.expenses.domain.usecase

import com.example.shmryandex.core.data.network.model.Result
import com.example.shmryandex.core.domain.entity.Account
import com.example.shmryandex.feature.expenses.domain.entity.Expense
import com.example.shmryandex.feature.expenses.domain.repository.ExpensesRepository
import javax.inject.Inject

class GetExpensesListUseCase @Inject constructor(
    private val repository: ExpensesRepository
) {
    suspend operator fun invoke(accounts: List<Account>): Result<List<Expense>> {
        return repository.getExpensesList(accounts)
    }
}