package com.example.shmryandex.feature.accounts.data.repository

import com.example.shmryandex.core.data.network.model.Result
import com.example.shmryandex.feature.accounts.data.network.model.CreateAccountRequest
import com.example.shmryandex.feature.accounts.data.network.api.AccountsApi
import com.example.shmryandex.feature.accounts.domain.repository.AccountsRepository
import javax.inject.Inject

/**
 * Реализация репозитория для работы со счетами.
 * Обеспечивает создание новых счетов через API.
 */
class AccountsRepositoryImpl @Inject constructor(
    private val api: AccountsApi
): AccountsRepository {

    override suspend fun createAccount(
        name: String,
        balance: String,
        currency: String
    ): Result<Unit> = Result.execute {
        api.createAccount(
            CreateAccountRequest(
                name = name,
                balance = balance,
                currency = currency
            )
        )
    }
}