package com.example.accounts.impl.data.network.model

/**
 * Модель запроса для создания нового счета.
 * Содержит необходимые параметры: название счета, начальный баланс и валюту.
 */
data class AccountRequestBody(
    val name: String,
    val balance: String,
    val currency: String
)