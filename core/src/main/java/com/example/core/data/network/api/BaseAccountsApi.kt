package com.example.core.data.network.api

import com.example.core.data.network.model.AccountDto
import retrofit2.http.GET

/**
 * Базовый интерфейс API для работы со счетами.
 * Определяет основные методы для взаимодействия с серверной частью
 * в контексте операций со счетами пользователя.
 */
interface BaseAccountsApi {

    @GET("accounts")
    suspend fun getAccountsList(): List<AccountDto>
}