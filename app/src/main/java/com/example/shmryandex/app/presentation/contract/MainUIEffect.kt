package com.example.shmryandex.app.presentation.contract

import com.example.shmryandex.core.domain.entity.Account
import com.example.shmryandex.core.presentation.mvi.UIEffect

sealed class MainUIEffect: UIEffect {

    data class NavigateToEditAccountScreen(val encodedAccount: String): MainUIEffect()
}