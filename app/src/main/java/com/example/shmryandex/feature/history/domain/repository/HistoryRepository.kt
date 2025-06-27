package com.example.shmryandex.feature.history.domain.repository

import com.example.shmryandex.core.data.network.model.Result
import com.example.shmryandex.core.domain.entity.Account
import com.example.shmryandex.feature.history.domain.entity.HistoryItem

interface HistoryRepository {

    suspend fun getHistoryByPeriod(
        accounts: List<Account>,
        startDate: String,
        endDate: String,
        isIncome: Boolean
        ): Result<List<HistoryItem>>

}