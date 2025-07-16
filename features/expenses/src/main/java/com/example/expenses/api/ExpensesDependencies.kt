package com.example.expenses.api

import com.example.core.data.network.api.TransactionsApi
import com.example.core.data.storage.dao.TransactionsDao
import com.example.core.domain.repository.BaseAccountsRepository
import com.example.core.domain.repository.BaseTransactionsRepository
import com.example.core.domain.repository.CategoriesRepository
import com.example.core.domain.repository.NetworkRepository

interface ExpensesDependencies {

    val transactionsApi: TransactionsApi

    val baseAccountsRepository: BaseAccountsRepository

    val baseTransactionsRepository: BaseTransactionsRepository

    val categoriesRepository: CategoriesRepository

    val networkRepository: NetworkRepository

    val transactionsDao: TransactionsDao
}