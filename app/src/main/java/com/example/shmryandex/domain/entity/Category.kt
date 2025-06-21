package com.example.shmryandex.domain.entity

data class Category(
    val id: Long,
    val name: String,
    val emoji: String,
    val isIncome: Boolean
) {
}