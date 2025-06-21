package com.example.shmryandex.domain.usecase

import com.example.shmryandex.data.network.Result
import com.example.shmryandex.domain.FinanceRepository
import com.example.shmryandex.domain.entity.Account
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class GetAccountUseCase @Inject constructor(
    private val repository: FinanceRepository
) {
    operator fun invoke(): StateFlow<List<Account>> = repository.getAccountsList()
}