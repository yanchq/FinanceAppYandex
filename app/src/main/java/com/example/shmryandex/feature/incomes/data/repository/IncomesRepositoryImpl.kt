package com.example.shmryandex.feature.incomes.data.repository

import com.example.shmryandex.core.data.network.api.TransactionsApi
import com.example.shmryandex.core.data.network.model.Result
import com.example.shmryandex.core.domain.entity.Account
import com.example.shmryandex.feature.incomes.data.mapper.IncomesMapper
import com.example.shmryandex.feature.incomes.domain.entity.Income
import com.example.shmryandex.feature.incomes.domain.repository.IncomesRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import java.time.LocalDate
import javax.inject.Inject

class IncomesRepositoryImpl @Inject constructor(
    private val mapper: IncomesMapper,
    private val api: TransactionsApi
) : IncomesRepository {

    private val today = LocalDate.now().toString()

    override suspend fun getIncomesList(accounts: List<Account>): Result<List<Income>> =
        Result.execute {
            coroutineScope {
                accounts.map { account ->
                    async {
                        api.getTransactionsByAccountPeriod(
                            accountId = account.id,
                            startDate = today,
                            endDate = today
                        )
                    }
                }
            }.awaitAll().flatten()
                .filter { it.category.isIncome }
                .map { dto ->
                    mapper.mapTransactionDtoToIncome(dto)
                }
        }
}