package com.example.accounts.impl.domain.repository

import com.example.core.data.network.model.Result

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