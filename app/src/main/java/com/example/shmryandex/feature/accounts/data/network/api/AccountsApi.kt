package com.example.shmryandex.feature.accounts.data.network.api

import com.example.shmryandex.feature.accounts.data.network.model.AccountRequestBody
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Интерфейс API для управления счетами.
 * Предоставляет методы для создания и удаления счетов через REST API.
 */
interface AccountsApi {

    @POST("accounts")
    suspend fun createAccount(
        @Body requestBody: AccountRequestBody,
    )

    @DELETE("accounts")
    suspend fun deleteAccount(
        @Query("id") accountId: Int
    )

    @PUT("accounts/{id}")
    suspend fun editAccount(
        @Path("id") accoutId: Int,
        @Body requestBody: AccountRequestBody
    )
}