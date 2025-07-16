package com.example.incomes.api

import com.example.core.data.network.api.TransactionsApi
import com.example.core.data.storage.dao.TransactionsDao
import com.example.core.domain.repository.BaseAccountsRepository
import com.example.core.domain.repository.NetworkRepository

interface IncomesDependencies {

    val transactionsApi: TransactionsApi

    val baseAccountsRepository: BaseAccountsRepository

    val networkRepository: NetworkRepository

    val transactionsDao: TransactionsDao
}