package com.example.history.impl.domain.repository

import com.example.core.data.network.model.Result
import com.example.core.domain.entity.Account
import com.example.history.impl.domain.entity.HistoryItem

/**
 * Репозиторий для работы с историей транзакций.
 * Определяет методы для получения истории транзакций за определенный период.
 */
interface HistoryRepository {

    suspend fun getHistoryByPeriod(
        accounts: List<Account>,
        startDate: String,
        endDate: String,
        isIncome: Boolean
        ): Result<List<HistoryItem>>

}