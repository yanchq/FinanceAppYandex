package com.example.shmryandex.app.presentation.splash.contract

import com.example.core.presentation.mvi.UIEffect

/**
 * Sealed класс, определяющий одноразовые UI-эффекты для сплеш-экрана.
 * Содержит эффекты навигации после завершения загрузки приложения.
 */
sealed class SplashUIEffect: UIEffect {
    data object NavigateToMainScreen : SplashUIEffect()
}