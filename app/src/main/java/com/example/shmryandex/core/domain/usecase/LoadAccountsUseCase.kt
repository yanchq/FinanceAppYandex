package com.example.shmryandex.core.domain.usecase

import com.example.shmryandex.core.data.network.model.Result
import com.example.shmryandex.core.domain.entity.Account
import com.example.shmryandex.core.domain.repository.BaseAccountsRepository
import javax.inject.Inject

/**
 * Use case для загрузки списка счетов с сервера.
 * Возвращает Result с списком счетов в случае успеха или ошибку в случае неудачи.
 */
class LoadAccountsUseCase @Inject constructor(
    private val repository: BaseAccountsRepository
) {
    suspend operator fun invoke(): Result<List<Account>> {
        return repository.loadAccounts()
    }
}