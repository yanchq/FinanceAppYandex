package com.example.shmryandex.core.utils


fun String.convertCurrency(): String {
    when (this) {
        "RUB" -> return "₽"
        "USD" -> return "$"
        "EUR" -> return "€"
        else -> return "₽"
    }
}