package com.example.core.data.repository

import com.example.core.data.mapper.AccountMapper
import com.example.core.data.network.api.BaseAccountsApi
import com.example.core.data.network.model.Result
import com.example.core.domain.entity.Account
import com.example.core.domain.repository.BaseAccountsRepository
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
    private var selectedAccount = MutableStateFlow<Account?>(null)

    override suspend fun loadAccounts(): Result<List<Account>> = Result.execute {
        accounts.value = accountsApi.getAccountsList().map { dto ->
            mapper.mapAccountDtoToDomain(dto)
        }
        selectedAccount.value?.let { selected ->
            selectedAccount.value = accounts.value.find { it.id == selected.id }
        }
        accounts.value
    }

    override suspend fun getAccountsList(): List<Account> = accounts.value

    override suspend fun getAccountsFlow(): StateFlow<List<Account>> = accounts

    override fun setSelectedAccount(account: Account) {
        selectedAccount.value = account
    }

    override suspend fun getSelectedAccountFlow(): StateFlow<Account?> = selectedAccount

    override suspend fun getSelectedAccount(): Account? = selectedAccount.value
}