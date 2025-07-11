package com.example.core.domain.usecase

import com.example.core.domain.entity.Account
import com.example.core.domain.repository.BaseAccountsRepository
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

/**
 * Use case для получения потока данных о счетах.
 * Предоставляет реактивный доступ к списку счетов через StateFlow,
 * что позволяет автоматически обновлять UI при изменении данных.
 */
class GetAccountsFlowUseCase @Inject constructor(
    private val repository: BaseAccountsRepository
) {

    suspend operator fun invoke(): StateFlow<List<Account>> {
        return repository.getAccountsFlow()
    }
}