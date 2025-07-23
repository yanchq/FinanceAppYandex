package com.example.shmryandex.app.presentation.options.main.contract

import com.example.core.presentation.mvi.UIEvent

sealed interface OptionsUIEvent: UIEvent {

    data object ThemeToggleClicked: OptionsUIEvent

    data object HapticButtonClicked: OptionsUIEvent
}