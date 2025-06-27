package com.example.shmryandex.core.domain.entity

/**
 * Доменная модель счета.
 * Представляет основную бизнес-сущность счета в приложении, содержащую информацию
 * о балансе, валюте и других характеристиках счета.
 */
data class Account(
    val id: Int,
    val name: String,
    val balance: Double,
    val currency: String
)
