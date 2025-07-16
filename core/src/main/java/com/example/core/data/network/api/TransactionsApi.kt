package com.example.core.data.network.api

import com.example.core.data.network.model.CreateTransactionRequestBody
import com.example.core.data.network.model.CreateTransactionResponse
import com.example.core.data.network.model.TransactionDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Интерфейс API для работы с транзакциями.
 * Предоставляет методы для получения списка транзакций по счету
 * с возможностью фильтрации по временному периоду.
 */
interface TransactionsApi {

    @GET("transactions/account/{accountId}/period")
    suspend fun getTransactionsByAccountPeriod(
        @Path("accountId") accountId: Int,
        @Query("startDate") startDate: String?,
        @Query("endDate") endDate: String?,
    ): List<TransactionDto>

    @POST("transactions")
    suspend fun createTransaction(
        @Body requestBody: CreateTransactionRequestBody
    ): CreateTransactionResponse

    @PUT("transactions/{id}")
    suspend fun editTransaction(
        @Path("id") transactionId: Int,
        @Body requestBody: CreateTransactionRequestBody
    )

    @GET("transactions/{id}")
    suspend fun getTransactionById(
        @Path("id") transactionId: Int
    ): TransactionDto
}