package com.example.shmryandex.core.domain.repository

import com.example.shmryandex.core.data.network.model.Result
import com.example.shmryandex.core.domain.entity.Account
import kotlinx.coroutines.flow.StateFlow

/**
 * Базовый репозиторий для работы со счетами.
 * Определяет методы для загрузки, получения списка счетов и наблюдения за их изменениями.
 */
interface BaseAccountsRepository {

    suspend fun loadAccounts(): Result<List<Account>>
    suspend fun getAccountsList(): List<Account>
    suspend fun getAccountsFlow(): StateFlow<List<Account>>
}