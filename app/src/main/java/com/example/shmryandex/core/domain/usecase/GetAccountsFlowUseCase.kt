package com.example.shmryandex.core.domain.usecase

import com.example.shmryandex.core.domain.entity.Account
import com.example.shmryandex.core.domain.repository.BaseAccountsRepository
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class GetAccountsFlowUseCase @Inject constructor(
    private val repository: BaseAccountsRepository
) {

    suspend operator fun invoke(): StateFlow<List<Account>> {
        return repository.getAccountsFlow()
    }
}