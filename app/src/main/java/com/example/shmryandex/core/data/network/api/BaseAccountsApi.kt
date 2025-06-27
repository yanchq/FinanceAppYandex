package com.example.shmryandex.core.data.network.api

import com.example.shmryandex.core.data.network.model.AccountDto
import retrofit2.http.GET

interface BaseAccountsApi {

    @GET("accounts")
    suspend fun getAccountsList(): List<AccountDto>
}