package com.example.shmryandex.feature.accounts.domain.repository

import com.example.shmryandex.core.data.network.model.Result

/**
 * Интерфейс репозитория для управления счетами.
 * Определяет методы для создания новых счетов в системе.
 */
interface AccountsRepository {

    suspend fun createAccount(
        name: String,
        balance: String,
        currency: String
    ): Result<Unit>

    suspend fun editAccount(
        accountId: Int,
        name: String,
        balance: String,
        currency: String
    ): Result<Unit>
}