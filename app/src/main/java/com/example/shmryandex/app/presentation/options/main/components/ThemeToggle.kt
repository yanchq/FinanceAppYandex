package com.example.shmryandex.app.presentation.options.main.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.example.core.utils.ui.localizedString
import com.example.core.utils.ui.theme.DividerGrey
import com.example.core.utils.ui.theme.PrimaryGreen
import com.example.shmryandex.R

@Composable
fun ThemeToggle(
    isDarkTheme: Boolean,
    onToggle: (Boolean) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .drawBehind {
                val strokeWidth = 0.7.dp.toPx()
                drawLine(
                    color = DividerGrey,
                    start = Offset(0f, size.height - strokeWidth / 2),
                    end = Offset(size.width, size.height - strokeWidth / 2),
                    strokeWidth = strokeWidth
                )
            }
            .padding(horizontal = 16.dp, vertical = 8.dp)

    ) {
        Text(
            modifier = Modifier.weight(1f),
            text = localizedString(R.string.dark_mode),
            style = MaterialTheme.typography.bodyMedium
        )
        Switch(
            checked = isDarkTheme,
            onCheckedChange = onToggle,
            modifier = Modifier.testTag("theme_toggle_switch"),
            colors = SwitchDefaults.colors(
                checkedThumbColor = White,
                uncheckedThumbColor = Color(0xFF79747E),
                checkedTrackColor = MaterialTheme.colorScheme.primaryContainer,
                uncheckedTrackColor = Color(0xFFE6E0E9)
            )
        )
    }
}