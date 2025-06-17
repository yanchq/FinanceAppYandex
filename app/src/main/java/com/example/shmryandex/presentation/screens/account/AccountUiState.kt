package com.example.shmryandex.presentation.screens.account

import com.example.shmryandex.domain.entity.Account

data class AccountUiState(
    val accounts: List<Account> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
) 