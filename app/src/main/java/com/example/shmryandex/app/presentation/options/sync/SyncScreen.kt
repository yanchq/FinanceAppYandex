package com.example.shmryandex.app.presentation.options.sync

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.core.presentation.LocalViewModelFactory

@Composable
fun SyncScreen(
    viewModel: SyncViewModel = viewModel(factory = LocalViewModelFactory.current)
) {

    var syncIntervalHours = viewModel.syncInterval.collectAsStateWithLifecycle()

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Интервал синхронизации (в часах)", style = MaterialTheme.typography.titleMedium)
            Text("${syncIntervalHours.value} ч")
            Box(modifier = Modifier.fillMaxWidth(0.8f)) {
                Slider(
                    value = syncIntervalHours.value.toFloat(),
                    onValueChange = { viewModel.setSyncInterval(it.toInt()) },
                    valueRange = 2f..6f,
                    steps = 3
                )
            }
        }
    }
}