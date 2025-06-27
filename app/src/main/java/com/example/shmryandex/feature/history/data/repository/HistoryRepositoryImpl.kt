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

/**
 * Реализация репозитория для работы с историей транзакций.
 * Обеспечивает получение данных о транзакциях из сетевого источника
 * с последующей фильтрацией и сортировкой по заданным параметрам.
 *
 * @property mapper Маппер для преобразования сетевых моделей в доменные
 * @property api API для работы с транзакциями
 */
class HistoryRepositoryImpl @Inject constructor(
    private val mapper: HistoryMapper,
    private val api: TransactionsApi
): HistoryRepository {

    /**
     * Получает историю транзакций за указанный период по списку счетов.
     * Выполняет параллельные запросы для каждого счета, объединяет результаты,
     * фильтрует по типу транзакции (доход/расход) и сортирует по дате.
     *
     * @param accounts Список счетов для получения транзакций
     * @param startDate Начальная дата периода в формате YYYY-MM-DD
     * @param endDate Конечная дата периода в формате YYYY-MM-DD
     * @param isIncome Флаг для фильтрации транзакций (true - доходы, false - расходы)
     * @return [Result] с отсортированным списком транзакций или ошибкой
     */
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