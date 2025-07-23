package com.example.shmryandex.app.presentation.options.main.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.core.presentation.LocalViewModelFactory
import com.example.shmryandex.app.presentation.options.main.components.OptionCard
import com.example.shmryandex.app.presentation.options.main.components.OptionsScreenContent
import com.example.shmryandex.app.presentation.options.main.components.ThemeToggle
import com.example.shmryandex.app.presentation.options.main.viewmodel.OptionsViewModel

@Composable
fun OptionsScreen(
    navHostController: NavHostController,
    viewModel: OptionsViewModel = viewModel(factory = LocalViewModelFactory.current)
) {

    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    OptionsScreenContent(
        uiState = uiState.value,
        navHostController = navHostController
    ) { event ->
        viewModel.onEvent(event)
    }
}
