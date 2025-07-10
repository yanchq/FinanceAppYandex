package com.example.core.domain.usecase

import com.example.core.domain.entity.Account
import com.example.core.domain.repository.BaseAccountsRepository
import javax.inject.Inject

class GetSelectedAccountUseCase @Inject constructor(
    private val repository: BaseAccountsRepository
) {

    suspend operator fun invoke(): Account? = repository.getSelectedAccount()
}