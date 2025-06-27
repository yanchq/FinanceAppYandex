package com.example.shmryandex.core.data.network.api

import com.example.shmryandex.core.data.network.model.TransactionDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TransactionsApi {

    @GET("transactions/account/{accountId}/period")
    suspend fun getTransactionsByAccountPeriod(
        @Path("accountId") accountId: Int,
        @Query("startDate") startDate: String?,
        @Query("endDate") endDate: String?,
    ): List<TransactionDto>
}