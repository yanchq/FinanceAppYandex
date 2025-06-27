package com.example.shmryandex.feature.history.data.repository

import com.example.shmryandex.core.data.network.api.TransactionsApi
import com.example.shmryandex.core.data.network.model.Result
import com.example.shmryandex.core.domain.entity.Account
import com.example.shmryandex.feature.history.data.mapper.HistoryMapper
import com.example.shmryandex.feature.history.domain.entity.HistoryItem
import com.example.shmryandex.feature.history.domain.repository.HistoryRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class HistoryRepositoryImpl @Inject constructor(
    private val mapper: HistoryMapper,
    private val api: TransactionsApi
): HistoryRepository {

    override suspend fun getHistoryByPeriod(
        accounts: List<Account>,
        startDate: String,
        endDate: String,
        isIncome: Boolean
    ): Result<List<HistoryItem>> = Result.execute {
        coroutineScope {
            accounts.map { account ->
                async {
                    api.getTransactionsByAccountPeriod(
                        accountId = account.id,
                        startDate = startDate,
                        endDate = endDate
                    )
                }
            }
        }.awaitAll().flatten()
            .filter { it.category.isIncome == isIncome }
            .sortedByDescending { it.createdAt }
            .map { mapper.mapTransactionDtoToHistoryItem(it) }
    }
}