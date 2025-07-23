package com.example.shmryandex.app.presentation.options.main.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.core.presentation.mvi.BaseViewModel
import com.example.shmryandex.app.domain.usecase.theme.GetThemeModeUseCase
import com.example.shmryandex.app.domain.usecase.theme.SetThemeModeUseCase
import com.example.shmryandex.app.domain.usecase.haptic.TriggerHapticUseCase
import com.example.shmryandex.app.presentation.options.main.contract.OptionsUIEffect
import com.example.shmryandex.app.presentation.options.main.contract.OptionsUIEvent
import com.example.shmryandex.app.presentation.options.main.contract.OptionsUIState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class OptionsViewModel @Inject constructor(
    private val getThemeModeUseCase: GetThemeModeUseCase,
    private val setThemeModeUseCase: SetThemeModeUseCase,
    private val triggerHapticUseCase: TriggerHapticUseCase
): BaseViewModel<OptionsUIEvent, OptionsUIState, OptionsUIEffect>
    (OptionsUIState()) {

        init {
            getThemeMode()
        }

    override fun onEvent(event: OptionsUIEvent) {
        when (event) {
            OptionsUIEvent.ThemeToggleClicked -> {
                setThemeMode(!currentState.isDarkTheme)
            }

            OptionsUIEvent.HapticButtonClicked -> {
                viewModelScope.launch(Dispatchers.IO) {
                    triggerHapticUseCase()
                }
            }
        }
    }

    private fun getThemeMode() {
        viewModelScope.launch(Dispatchers.IO) {
            val isDarkTheme = getThemeModeUseCase()
            setState(currentState.copy(
                isDarkTheme = isDarkTheme
            ))
        }
    }

    private fun setThemeMode(newThemeMode: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            setState(currentState.copy(
                isDarkTheme = newThemeMode
            ))
            setThemeModeUseCase(newThemeMode)
        }
    }
}