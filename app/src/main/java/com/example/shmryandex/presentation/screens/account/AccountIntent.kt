package com.example.shmryandex.presentation.screens.account

sealed interface AccountIntent {
    data object RefreshAccount : AccountIntent
}