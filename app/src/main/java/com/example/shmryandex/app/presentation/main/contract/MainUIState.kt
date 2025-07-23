package com.example.shmryandex.app.presentation.main.contract

import com.example.core.presentation.mvi.UIState

data class MainUIState(
    val state: Nothing? = null,
    val syncTime: Long = 0L,
    val syncStatus: String = ""
): UIState
