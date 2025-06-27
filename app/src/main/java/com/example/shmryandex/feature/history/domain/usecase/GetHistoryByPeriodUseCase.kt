package com.example.shmryandex.feature.history.domain.usecase

import com.example.shmryandex.core.data.network.model.Result
import com.example.shmryandex.core.domain.entity.Account
import com.example.shmryandex.feature.history.domain.entity.HistoryItem
import com.example.shmryandex.feature.history.domain.repository.HistoryRepository
import javax.inject.Inject

/**
 * Use case для получения истории транзакций за определенный период.
 * Принимает список счетов, начальную и конечную даты, а также флаг типа транзакции (доход/расход).
 * Возвращает Result с списком элементов истории в случае успеха или ошибку в случае неудачи.
 */
class GetHistoryByPeriodUseCase @Inject constructor(
    private val repository: HistoryRepository
) {

    suspend operator fun invoke(
        accounts: List<Account>,
        startDate: String,
        endDate: String,
        isIncome: Boolean
    ): Result<List<HistoryItem>> {
        return repository.getHistoryByPeriod(
            accounts = accounts,
            startDate = startDate,
            endDate = endDate,
            isIncome = isIncome
        )
    }
}