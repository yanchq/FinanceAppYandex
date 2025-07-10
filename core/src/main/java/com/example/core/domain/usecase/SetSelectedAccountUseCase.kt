package com.example.core.domain.usecase

import com.example.core.domain.entity.Account
import com.example.core.domain.repository.BaseAccountsRepository
import javax.inject.Inject

class SetSelectedAccountUseCase @Inject constructor(
    private val repository: BaseAccountsRepository
) {

    operator fun invoke(account: Account) {
        repository.setSelectedAccount(account)
    }
}