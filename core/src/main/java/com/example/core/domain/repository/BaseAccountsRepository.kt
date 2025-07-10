package com.example.core.domain.repository

import com.example.core.data.network.model.Result
import com.example.core.domain.entity.Account
import kotlinx.coroutines.flow.StateFlow

/**
 * Базовый репозиторий для работы со счетами.
 * Определяет методы для загрузки, получения списка счетов и наблюдения за их изменениями.
 */
interface BaseAccountsRepository {

    suspend fun loadAccounts(): Result<List<Account>>
    suspend fun getAccountsList(): List<Account>
    suspend fun getAccountsFlow(): StateFlow<List<Account>>
    fun setSelectedAccount(account: Account)
    suspend fun getSelectedAccountFlow(): StateFlow<Account?>
    suspend fun getSelectedAccount(): Account?
}