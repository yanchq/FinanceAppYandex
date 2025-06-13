package com.example.shmryandex.presentation.screens.options

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.shmryandex.R
import com.example.shmryandex.ui.theme.DividerGrey
import com.example.shmryandex.ui.theme.PrimaryGreen

@Composable
fun OptionsScreen() {

    var isDarkTheme by remember { mutableStateOf(false) }

    Column {
        ThemeToggle(
            isDarkTheme = isDarkTheme
        ) { isDarkTheme = it }
        OptionCard("Основной цвет")
        OptionCard("Звуки")
        OptionCard("Хаптики")
        OptionCard("Код пароль")
        OptionCard("Синхронизация")
        OptionCard("Язык")
        OptionCard("О программе")
    }
}

@Composable
private fun OptionCard(name: String) {

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
            modifier = Modifier
                .weight(1f),
            text = name,
            style = MaterialTheme.typography.bodyMedium
        )

        Image(
            painter = painterResource(R.drawable.ic_category_more),
            contentDescription = null
        )
    }
}

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
            text = "Тёмная тема",
            style = MaterialTheme.typography.bodyMedium
        )
        Switch(
            checked = isDarkTheme,
            onCheckedChange = onToggle,
            colors = SwitchDefaults.colors(
                checkedThumbColor = White,
                uncheckedThumbColor = Color(0xFF79747E),
                checkedTrackColor = PrimaryGreen,
                uncheckedTrackColor = Color(0xFFE6E0E9)
            )
        )
    }
}