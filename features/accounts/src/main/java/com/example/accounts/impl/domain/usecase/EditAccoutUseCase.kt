package com.example.accounts.impl.domain.usecase

import com.example.core.data.network.model.Result
import com.example.accounts.impl.domain.repository.AccountsRepository
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