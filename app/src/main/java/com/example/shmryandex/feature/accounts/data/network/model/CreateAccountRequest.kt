package com.example.shmryandex.feature.accounts.data.network.model

data class CreateAccountRequest(
    val name: String,
    val balance: String,
    val currency: String
)