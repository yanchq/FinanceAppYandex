package com.example.shmryandex.core.data.repository

import com.example.shmryandex.core.data.mapper.AccountMapper
import com.example.shmryandex.core.data.network.api.BaseAccountsApi
import com.example.shmryandex.core.data.network.model.Result
import com.example.shmryandex.core.domain.entity.Account
import com.example.shmryandex.core.domain.repository.BaseAccountsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

/**
 * Реализация базового репозитория для работы со счетами.
 * Обеспечивает загрузку и кэширование списка счетов,
 * предоставляет доступ к данным через Flow для реактивного обновления UI.
 */
class BaseAccountsRepositoryImpl @Inject constructor(
    private val mapper: AccountMapper,
    private val accountsApi: BaseAccountsApi
): BaseAccountsRepository {

    private val accounts = MutableStateFlow<List<Account>>(emptyList())

    override suspend fun loadAccounts(): Result<List<Account>> = Result.execute {
        accounts.value = accountsApi.getAccountsList().map { dto ->
            mapper.mapAccountDtoToDomain(dto)
        }
        accounts.value
    }

    override suspend fun getAccountsList(): List<Account> {
        return accounts.value
    }

    override suspend fun getAccountsFlow(): StateFlow<List<Account>> {
        return accounts
    }


}