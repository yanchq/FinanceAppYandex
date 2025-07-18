package com.example.core.presentation.mvi

sealed interface ScreenContent {

    data object Content: ScreenContent

    data object Error: ScreenContent

    data object Loading: ScreenContent
}