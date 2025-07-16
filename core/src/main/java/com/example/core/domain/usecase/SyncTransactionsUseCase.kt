package com.example.core.domain.usecase

import com.example.core.data.network.model.Result
import com.example.core.domain.repository.BaseTransactionsRepository
import javax.inject.Inject


class SyncTransactionsUseCase @Inject constructor(
    private val repository: BaseTransactionsRepository
) {

    suspend operator fun invoke(): Result<Unit> {
        return repository.syncTransactions()
    }
}