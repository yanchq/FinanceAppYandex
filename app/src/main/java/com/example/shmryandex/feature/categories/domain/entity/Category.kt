package com.example.shmryandex.feature.categories.domain.entity

data class Category(
    val id: Long,
    val name: String,
    val emoji: String,
    val isIncome: Boolean
) {
}