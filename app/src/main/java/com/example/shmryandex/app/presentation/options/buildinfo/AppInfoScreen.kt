package com.example.shmryandex.app.presentation.options.buildinfo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AppInfoScreen() {
    val context = LocalContext.current
    val appInfo = remember { context.getAppInfo() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Версия приложения: ${appInfo.versionName}", fontSize = 18.sp)
        Spacer(modifier = Modifier.height(12.dp))
        Text(text = "Дата последнего обновления: ${appInfo.lastUpdate}", fontSize = 18.sp)
    }
}
