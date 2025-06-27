package com.example.shmryandex.feature.accounts.domain.repository

import com.example.shmryandex.core.data.network.model.Result

interface AccountsRepository {

    suspend fun createAccount(
        name: String,
        balance: String,
        currency: String
    ): Result<Unit>
}