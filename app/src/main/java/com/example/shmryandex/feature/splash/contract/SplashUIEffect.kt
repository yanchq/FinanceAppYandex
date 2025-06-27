package com.example.shmryandex.feature.splash.contract

import com.example.shmryandex.core.presentation.mvi.UIEffect

sealed class SplashUIEffect: UIEffect {
    data object NavigateToMainScreen : SplashUIEffect()
}