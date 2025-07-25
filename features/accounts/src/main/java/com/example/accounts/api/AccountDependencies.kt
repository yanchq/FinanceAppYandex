package com.example.accounts.api

import com.example.core.domain.repository.BaseAccountsRepository
import com.example.core.domain.repository.BaseTransactionsRepository
import retrofit2.Retrofit

interface AccountDependencies {

    val baseAccountsRepository: BaseAccountsRepository

    val baseTransactionsRepository: BaseTransactionsRepository

    val retrofit: Retrofit
}