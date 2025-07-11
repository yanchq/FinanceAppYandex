package com.example.accounts.impl.data.repository

import android.util.Log
import com.example.core.data.network.model.Result
import com.example.accounts.impl.data.network.model.AccountRequestBody
import com.example.accounts.impl.data.network.api.AccountsApi
import com.example.accounts.impl.di.AccountsScope
import com.example.accounts.impl.domain.repository.AccountsRepository
import javax.inject.Inject

/**
 * Реализация репозитория для работы со счетами.
 * Обеспечивает создание новых счетов через API.
 */
class AccountsRepositoryImpl @Inject constructor(
    private val api: AccountsApi
): AccountsRepository {

    var count = 0

    override suspend fun createAccount(
        name: String,
        balance: String,
        currency: String
    ): Result<Unit> = Result.execute {
        api.createAccount(
            AccountRequestBody(
                name = name,
                balance = balance,
                currency = currency
            )
        )
    }

    override suspend fun editAccount(
        accountId: Int,
        name: String,
        balance: String,
        currency: String
    ): Result<Unit> = Result.execute {
        count++
        Log.d("ScopesTest", count.toString())
        api.editAccount(
            accoutId = accountId,
            AccountRequestBody(
                name = name,
                balance = balance,
                currency = currency
            )
        )
    }
}