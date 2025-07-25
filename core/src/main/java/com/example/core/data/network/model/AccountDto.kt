package com.example.core.data.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * DTO класс для счета.
 * Используется для сериализации/десериализации данных при работе с API.
 */
@JsonClass(generateAdapter = true)
data class AccountDto(
    @Json(name = "id") val id: Int,
    @Json(name = "name") val name: String,
    @Json(name = "balance") val balance: String,
    @Json(name = "currency") val currency: String
) 