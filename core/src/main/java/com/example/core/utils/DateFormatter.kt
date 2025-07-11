package com.example.core.utils

import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId
import java.time.ZoneOffset
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

fun getLocalTime(): String {
    val currentTime = LocalTime.now()
    val formatter = DateTimeFormatter.ofPattern("HH:mm")
    val formattedTime = currentTime.format(formatter)
    return formattedTime
}

fun LocalTime.formatToString(): String {
    val formatter = DateTimeFormatter.ofPattern("HH:mm")
    return this.format(formatter)
}

fun String.toLocalTime(): LocalTime {
    val formatter = DateTimeFormatter.ofPattern("HH:mm")
    return LocalTime.parse(this, formatter)
}

fun toUtcIsoString(dateString: String, timeString: String): String {
    val date = LocalDate.parse(dateString)
    val time = LocalTime.parse(timeString)
    val dateTime = LocalDateTime.of(date, time)

    // Преобразуем в ZonedDateTime в текущей временной зоне, затем в UTC
    val zonedDateTime = dateTime.atZone(ZoneId.systemDefault()).withZoneSameInstant(ZoneOffset.UTC)

    return DateTimeFormatter.ISO_INSTANT.format(zonedDateTime.toInstant())
}