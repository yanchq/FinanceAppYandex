package com.example.shmryandex.domain.entity

data class Income(
    val id: Int,
    val category: Category,
    val amount: Int,
    val comment: String = ""
) {
}