package com.example.shmryandex.data.network.model

data class CreateAccountRequest(
    val name: String,
    val balance: String,
    val currency: String
)