package com.example.shmryandex.feature.accounts.domain.usecase

import com.example.shmryandex.core.data.network.model.Result
import com.example.shmryandex.feature.accounts.domain.repository.AccountsRepository
import javax.inject.Inject

/**
 * Use case для создания нового счета.
 * Принимает название счета, баланс и валюту.
 * Возвращает Result с Unit в случае успеха или ошибку в случае неудачи.
 */
class CreateAccountUseCase @Inject constructor(
    private val repository: AccountsRepository
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