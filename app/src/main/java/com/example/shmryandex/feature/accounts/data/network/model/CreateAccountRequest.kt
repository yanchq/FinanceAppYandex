package com.example.shmryandex.feature.accounts.data.network.model

/**
 * Модель запроса для создания нового счета.
 * Содержит необходимые параметры: название счета, начальный баланс и валюту.
 */
data class CreateAccountRequest(
    val name: String,
    val balance: String,
    val currency: String
)