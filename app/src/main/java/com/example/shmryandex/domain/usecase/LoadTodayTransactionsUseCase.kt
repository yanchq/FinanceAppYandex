package com.example.shmryandex.domain.usecase

import com.example.shmryandex.data.network.Result
import com.example.shmryandex.domain.FinanceRepository
import javax.inject.Inject

class LoadTodayTransactionsUseCase @Inject constructor(
    private val repository: FinanceRepository
) {
    suspend operator fun invoke(): Result<Unit> {
        return repository.loadTodayTransactions()
    }
}