package com.example.shmryandex.feature.splash.contract

import com.example.shmryandex.core.presentation.mvi.UIEffect

/**
 * Sealed класс, определяющий одноразовые UI-эффекты для сплеш-экрана.
 * Содержит эффекты навигации после завершения загрузки приложения.
 */
sealed class SplashUIEffect: UIEffect {
    data object NavigateToMainScreen : SplashUIEffect()
}