package com.example.shmryandex.app.presentation.options.main.components

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.core.utils.ui.localizedString
import com.example.shmryandex.R
import com.example.shmryandex.app.navigation.Screen
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
            name = localizedString(R.string.change_main_color_title),
            onClick = {
                sendEvent(OptionsUIEvent.HapticButtonClicked)
                navHostController.navigate(Screen.ChangeMainColor.route)
            }
        )
        OptionCard(
            name = localizedString(R.string.sounds_title),
            onClick = {
                sendEvent(OptionsUIEvent.HapticButtonClicked)
            }
        )
        OptionCard(
            name = localizedString(R.string.haptics_title),
            onClick = {
                sendEvent(OptionsUIEvent.HapticButtonClicked)
                navHostController.navigate(Screen.SelectHaptic.route)
            }
        )
        OptionCard(
            name = localizedString(R.string.app_password_title),
            onClick = {
                sendEvent(OptionsUIEvent.HapticButtonClicked)
            }
        )
        OptionCard(
            name = localizedString(R.string.sync_title),
            onClick = {
                sendEvent(OptionsUIEvent.HapticButtonClicked)
                navHostController.navigate(Screen.SyncInterval.route)
            }
        )
        OptionCard(
            name = localizedString(R.string.change_locale_title),
            onClick = {
                sendEvent(OptionsUIEvent.HapticButtonClicked)
                navHostController.navigate(Screen.ChangeLocale.route)
            }
        )
        OptionCard(
            name = localizedString(R.string.app_info_title),
            onClick = {
                sendEvent(OptionsUIEvent.HapticButtonClicked)
                navHostController.navigate(Screen.AppInfo.route)
            }
        )
    }
}