package com.example.shmryandex.app.data.repository

import android.util.Log
import com.example.core.data.mapper.AccountMapper
import com.example.core.data.network.api.BaseAccountsApi
import com.example.core.data.network.model.Result
import com.example.core.domain.entity.Account
import com.example.core.domain.repository.BaseAccountsRepository
import com.example.core.data.storage.dao.AccountDao
import com.example.core.domain.repository.NetworkRepository
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
    private val accountsApi: BaseAccountsApi,
    private val accountDao: AccountDao,
    private val networkRepository: NetworkRepository
): BaseAccountsRepository {

    private val accounts = MutableStateFlow<List<Account>>(emptyList())
    private var selectedAccount = MutableStateFlow<Account?>(null)

    override suspend fun loadAccounts(): Result<List<Account>> {
        Log.d("LoadFromDbTest", networkRepository.getNetworkStatus().value.toString())
        return if (networkRepository.getNetworkStatus().value) {
            loadAccountsFromNetwork()
        } else {
            loadAccountsFromDb()
        }
    }

    override suspend fun getAccountsList(): List<Account> = accounts.value

    override suspend fun getAccountsFlow(): StateFlow<List<Account>> = accounts

    override fun setSelectedAccount(account: Account) {
        selectedAccount.value = account
    }

    override suspend fun getSelectedAccountFlow(): StateFlow<Account?> = selectedAccount

    override suspend fun getSelectedAccount(): Account? = selectedAccount.value

    private fun updateSelectedAccount() {
        selectedAccount.value?.let { selected ->
            selectedAccount.value = accounts.value.find { it.id == selected.id }
        }
    }

    private suspend fun loadAccountsFromNetwork(): Result<List<Account>> = Result.execute {
        val accountDtos = accountsApi.getAccountsList()
        val domainAccounts = accountDtos.map { dto ->
            mapper.mapAccountDtoToDomain(dto)
        }

        // Сохраняем в БД
        val dbAccounts = domainAccounts.map { account ->
            mapper.mapDomainToDb(account)
        }
        accountDao.insertAccounts(dbAccounts)

        accounts.value = domainAccounts
        updateSelectedAccount()
        domainAccounts
    }

    private suspend fun loadAccountsFromDb(): Result<List<Account>> = Result.execute {
        val dbAccounts = accountDao.getAllAccounts()
        val domainAccounts = mapper.mapDbToDomainList(dbAccounts)

        accounts.value = domainAccounts
        updateSelectedAccount()
        domainAccounts
    }
}