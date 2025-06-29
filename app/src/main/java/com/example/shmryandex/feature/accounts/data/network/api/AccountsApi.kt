package com.example.shmryandex.feature.accounts.data.network.api

import com.example.shmryandex.feature.accounts.data.network.model.CreateAccountRequest
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * Интерфейс API для управления счетами.
 * Предоставляет методы для создания и удаления счетов через REST API.
 */
interface AccountsApi {

    @POST("accounts")
    suspend fun createAccount(
        @Body requestBody: CreateAccountRequest,
    )

    @DELETE("accounts")
    suspend fun deleteAccount(
        @Query("id") accountId: Int
    )
}