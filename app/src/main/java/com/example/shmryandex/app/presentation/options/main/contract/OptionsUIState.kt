package com.example.shmryandex.app.presentation.options.main.contract

import com.example.core.presentation.mvi.UIState

data class OptionsUIState(
    val isDarkTheme: Boolean = false
): UIState
