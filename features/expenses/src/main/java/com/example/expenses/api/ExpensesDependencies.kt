package com.example.expenses.api

import com.example.core.data.network.api.TransactionsApi
import com.example.core.domain.repository.BaseAccountsRepository

interface ExpensesDependencies {

    val transactionsApi: TransactionsApi

    val baseAccountsRepository: BaseAccountsRepository
}