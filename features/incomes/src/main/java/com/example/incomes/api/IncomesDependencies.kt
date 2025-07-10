package com.example.incomes.api

import com.example.core.data.network.api.TransactionsApi
import com.example.core.domain.repository.BaseAccountsRepository

interface IncomesDependencies {

    val transactionsApi: TransactionsApi

    val baseAccountsRepository: BaseAccountsRepository
}