package com.example.shmryandex.core.data.network.model

import com.example.shmryandex.feature.categories.data.network.model.CategoryDto
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

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