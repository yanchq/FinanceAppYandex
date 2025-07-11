package com.example.core.domain.usecase

import com.example.core.data.network.model.Result
import com.example.core.domain.entity.DetailedTransaction
import com.example.core.domain.repository.BaseTransactionsRepository
import javax.inject.Inject

class GetDetailedTransactionUseCase @Inject constructor(
    private val repository: BaseTransactionsRepository
) {
    suspend operator fun invoke(id: Int): Result<DetailedTransaction> {
        return repository.getTransactionById(id)
    }
}