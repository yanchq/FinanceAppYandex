package com.example.shmryandex.domain.usecase

import com.example.shmryandex.domain.FinanceRepository
import com.example.shmryandex.domain.entity.Expense
import javax.inject.Inject

class GetExpensesListUseCase @Inject constructor(
    private val repository: FinanceRepository
) {

    operator fun invoke(): List<Expense> {
        return repository.getExpensesList()
    }
}