package com.example.shmryandex.feature.categories.data.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * DTO класс для категории транзакции.
 * Используется для сериализации/десериализации данных при работе с API.
 */
@JsonClass(generateAdapter = true)
data class CategoryDto(
    @Json(name = "id") val id: Long,
    @Json(name = "name") val name: String,
    @Json(name = "emoji") val emoji: String,
    @Json(name = "isIncome") val isIncome: Boolean
) 