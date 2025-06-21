package com.example.shmryandex.presentation.screens.account

import com.example.shmryandex.domain.entity.Account

sealed interface AccountIntent {
    data object RefreshAccount : AccountIntent
    data class SelectAccount(val account: Account): AccountIntent
}