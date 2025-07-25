package com.example.core.domain.usecase

import com.example.core.data.network.model.Result
import com.example.core.domain.entity.Account
import com.example.core.domain.entity.Transaction
import com.example.core.domain.repository.BaseTransactionsRepository
import javax.inject.Inject

class GetTransactionsByPeriodUseCase @Inject constructor(
    private val repository: BaseTransactionsRepository
) {
    suspend operator fun invoke(
        accounts: List<Account>,
        startDate: String,
        endDate: String
    ): Result<List<Transaction>> = repository.getTransactionsByPeriod(
        accounts = accounts,
        startDate = startDate,
        endDate = endDate
    )
}