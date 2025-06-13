package com.example.shmryandex.domain.usecase

import com.example.shmryandex.domain.FinanceRepository
import com.example.shmryandex.domain.entity.Account
import javax.inject.Inject

class GetAccountUseCase @Inject constructor(
    private val repository: FinanceRepository
) {
    operator fun invoke(): Account {
        return repository.getAccount()
    }
}