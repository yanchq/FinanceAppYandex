package com.example.accounts.api

import com.example.core.domain.repository.BaseAccountsRepository
import retrofit2.Retrofit

interface AccountDependencies {

    val baseAccountsRepository: BaseAccountsRepository

    val retrofit: Retrofit
}