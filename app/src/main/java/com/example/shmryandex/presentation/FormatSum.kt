package com.example.shmryandex.presentation

fun Int.toCurrencyString(currency: String = "₽"): String {
    return "%,d".format(this).replace(',', ' ') + " $currency"
}

fun Double.toCurrencyString(currency: String = "₽"): String {
    return "%,.2f".format(this).replace(',', ' ') + " $currency"
}

