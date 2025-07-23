package com.example.shmryandex.app.presentation.options.main.components

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.shmryandex.app.presentation.options.main.contract.OptionsUIEvent
import com.example.shmryandex.app.presentation.options.main.contract.OptionsUIState

@Composable
fun OptionsScreenContent(
    uiState: OptionsUIState,
    navHostController: NavHostController,
    sendEvent: (OptionsUIEvent) -> Unit
) {

    Column {
        ThemeToggle(
            isDarkTheme = uiState.isDarkTheme
        ) { sendEvent(OptionsUIEvent.ThemeToggleClicked) }
        OptionCard(
            name = "Основной цвет",
            onClick = {
                sendEvent(OptionsUIEvent.HapticButtonClicked)
            }
        )
        OptionCard(
            name = "Звуки",
            onClick = {
                sendEvent(OptionsUIEvent.HapticButtonClicked)
            }
        )
        OptionCard(
            name = "Хаптики",
            onClick = {
                sendEvent(OptionsUIEvent.HapticButtonClicked)
            }
        )
        OptionCard(
            name = "Код пароль",
            onClick = {
                sendEvent(OptionsUIEvent.HapticButtonClicked)
            }
        )
        OptionCard(
            name = "Синхронизация",
            onClick = {
                sendEvent(OptionsUIEvent.HapticButtonClicked)
            }
        )
        OptionCard(
            name = "Язык",
            onClick = {
                sendEvent(OptionsUIEvent.HapticButtonClicked)
            }
        )
        OptionCard(
            name = "О программе",
            onClick = {
                sendEvent(OptionsUIEvent.HapticButtonClicked)
                navHostController.navigate("AppInfoRoute")
            }
        )
    }
}