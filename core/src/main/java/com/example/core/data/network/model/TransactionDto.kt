package com.example.core.data.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * DTO класс для транзакции.
 * Используется для сериализации/десериализации данных при работе с API.
 * Содержит информацию о счете, категории, сумме и времени транзакции.
 */
@JsonClass(generateAdapter = true)
data class TransactionDto(
    @Json(name = "id") val id: Long,
    @Json(name = "account") val account: AccountDto,
    @Json(name = "category") val category: CategoryDto,
    @Json(name = "amount") val amount: String,
    @Json(name = "transactionDate") val transactionDate: String,
    @Json(name = "comment") val comment: String,
    @Json(name = "createdAt") val createdAt: String,
    @Json(name = "updatedAt") val updatedAt: String
) 