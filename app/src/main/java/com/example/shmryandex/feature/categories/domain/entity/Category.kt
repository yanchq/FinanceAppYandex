package com.example.shmryandex.feature.categories.domain.entity

/**
 * Доменная модель категории транзакции.
 * Содержит основную информацию о категории: идентификатор, название,
 * эмодзи-иконку и признак принадлежности к доходам/расходам.
 */
data class Category(
    val id: Long,
    val name: String,
    val emoji: String,
    val isIncome: Boolean
) {
}