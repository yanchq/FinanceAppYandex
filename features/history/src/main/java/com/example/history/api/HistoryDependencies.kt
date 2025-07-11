package com.example.history.api

import com.example.core.data.network.api.TransactionsApi
import com.example.core.domain.repository.BaseAccountsRepository

interface HistoryDependencies {

    val transactionsApi: TransactionsApi

    val baseAccountsRepository: BaseAccountsRepository
}