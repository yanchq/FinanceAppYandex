package com.example.shmryandex.core.domain.usecase

import com.example.shmryandex.core.domain.entity.Account
import com.example.shmryandex.core.domain.repository.BaseAccountsRepository
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class GetAccountsListUseCase @Inject constructor(
    private val repository: BaseAccountsRepository
) {

    suspend operator fun invoke(): List<Account> {
        return repository.getAccountsList()
    }
}