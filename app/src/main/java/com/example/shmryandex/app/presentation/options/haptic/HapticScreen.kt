package com.example.shmryandex.app.presentation.options.haptic

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.core.data.haptic.HapticType
import com.example.core.presentation.LocalViewModelFactory

@Composable
fun HapticScreen(
    viewModel: HapticViewModel = viewModel(factory = LocalViewModelFactory.current)
) {

    val hapticType = viewModel.hapticType.collectAsStateWithLifecycle()
    val hapticEnabledState = viewModel.hapticEnabledState.collectAsStateWithLifecycle()

    Column {
        ThemeCard(
            title = "Vibration",
            isDarkTheme = hapticEnabledState.value,
            onToggle = { viewModel.setHapticEnabledState(!hapticEnabledState.value) }
        )
        Spacer(modifier = Modifier.size(32.dp))
        TextRadioButtonsRow(
            selected = hapticType.value,
            onSelectedChange = { type ->
                viewModel.setHapticType(type)
            },
            isEnabled = hapticEnabledState.value
        )
    }
}

@Composable
fun TextRadioButtonsRow(
    selected: HapticType,
    onSelectedChange: (HapticType) -> Unit,
    buttonSize: Dp = 70.dp,
    isEnabled: Boolean
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround,
    ) {
        HapticType.entries.forEach { hapticType ->
            val isSelected = hapticType == selected && isEnabled
            Box(
                modifier = Modifier
                    .size(buttonSize)
                    .background(
                        color = if (isSelected) MaterialTheme.colorScheme.primaryContainer else Color.Gray,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .clickable { onSelectedChange(hapticType) }
            ) {
                Text(
                    text = hapticType.value,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}