package com.example.shmryandex.feature.history.presentation.components

import androidx.compose.runtime.remember
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale

fun String.formatDateToMillis(): Long {

    val formatter = DateTimeFormatter.ISO_LOCAL_DATE

    val localDate = LocalDate.parse(this, formatter)
    return localDate
        .atStartOfDay(ZoneId.systemDefault())
        .toInstant()
        .toEpochMilli()
}

fun Long.formatDateToString(): String {
    val dateFormatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    return dateFormatter.format(Date(this))
}