package com.example.shmryandex.app.presentation.main.contract

import com.example.core.presentation.mvi.UIEffect

sealed class MainUIEffect: UIEffect {

    data class NavigateToEditAccountScreen(val encodedAccount: String): MainUIEffect()
}