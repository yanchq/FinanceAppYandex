package com.example.incomes.impl.data.repository

import android.util.Log
import androidx.compose.ui.platform.LocalGraphicsContext
import com.example.core.data.network.api.TransactionsApi
import com.example.core.data.network.model.Result
import com.example.core.domain.entity.Account
import com.example.incomes.impl.data.mapper.IncomesMapper
import com.example.incomes.impl.domain.entity.Income
import com.example.incomes.impl.domain.repository.IncomesRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import java.time.LocalDate
import javax.inject.Inject

/**
 * Реализация репозитория для работы с доходами.
 * Обеспечивает получение данных о доходах из сетевого источника с параллельной загрузкой
 * для нескольких счетов и последующей фильтрацией транзакций по типу.
 */
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