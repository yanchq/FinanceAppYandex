package com.example.shmryandex.app.presentation.options.buildinfo

import android.content.Context
import com.example.shmryandex.BuildConfig
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

data class AppInfo(
    val versionName: String,
    val lastUpdate: String
)

fun Context.getAppInfo(): AppInfo {
    val packageInfo = packageManager.getPackageInfo(packageName, 0)
    val lastUpdateTime = packageInfo.lastUpdateTime

    // Формат: "2025-07-15 14:38"
    val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
    val formattedDateTime = formatter.format(Date(lastUpdateTime))

    return AppInfo(
        versionName = BuildConfig.VERSION_NAME,
        lastUpdate = formattedDateTime
    )
}
