package com.example.core.domain.usecase

import com.example.core.data.network.model.Result
import com.example.core.domain.repository.BaseTransactionsRepository
import javax.inject.Inject

class CreateTransactionUseCase @Inject constructor(
    private val repository: BaseTransactionsRepository
) {

    suspend operator fun invoke(
        accountId: Int,
        categoryId: Int,
        amount: String,
        transactionDate: String,
        comment: String
    ): Result<Unit> =
        repository.createTransaction(
            accountId = accountId,
            categoryId = categoryId,
            amount = amount,
            transactionDate = transactionDate,
            comment = comment
        )
}