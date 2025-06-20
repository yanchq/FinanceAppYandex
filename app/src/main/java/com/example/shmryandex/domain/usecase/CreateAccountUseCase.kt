package com.example.shmryandex.domain.usecase

import com.example.shmryandex.data.network.Result
import com.example.shmryandex.domain.FinanceRepository
import javax.inject.Inject

class CreateAccountUseCase @Inject constructor(
    private val repository: FinanceRepository
) {
    suspend operator fun invoke(
        name: String,
        balance: String,
        currency: String
    ): Result<Unit> {
        return repository.createAccount(
            name = name,
            balance = balance,
            currency = currency
        )
    }
}