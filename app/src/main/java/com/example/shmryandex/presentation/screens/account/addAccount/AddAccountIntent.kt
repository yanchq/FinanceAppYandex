package com.example.shmryandex.presentation.screens.account.addAccount

sealed class AddAccountIntent {
    data class AddAccount(
        val name: String,
        val balance: String,
        val currency: String
    ): AddAccountIntent()
}