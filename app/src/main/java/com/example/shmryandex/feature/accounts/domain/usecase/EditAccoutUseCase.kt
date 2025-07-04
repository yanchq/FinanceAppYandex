package com.example.shmryandex.feature.accounts.domain.usecase

import com.example.shmryandex.core.data.network.model.Result
import com.example.shmryandex.feature.accounts.domain.repository.AccountsRepository
import javax.inject.Inject

class EditAccountUseCase @Inject constructor(
    private val repository: AccountsRepository
) {

    suspend operator fun invoke(
        accountId: Int,
        name: String,
        balance: String,
        currency: String
    ): Result<Unit> {
        return repository.editAccount(
            accountId = accountId,
            name = name,
            balance = balance,
            currency = currency
        )
    }
}