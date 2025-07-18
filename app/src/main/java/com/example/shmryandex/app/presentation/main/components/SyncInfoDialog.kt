package com.example.shmryandex.app.presentation.main.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun SyncInfoDialog(
    lastSyncTime: Long,
    lastSyncStatus: String,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Информация о синхронизации") },
        text = {
            Column {
                val timeText = SimpleDateFormat("dd.MM.yyyy HH:mm:ss", Locale.getDefault())
                    .format(Date(lastSyncTime))

                Text("Время: $timeText")

                Text(lastSyncStatus)
            }
        },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text("OK")
            }
        }
    )
}