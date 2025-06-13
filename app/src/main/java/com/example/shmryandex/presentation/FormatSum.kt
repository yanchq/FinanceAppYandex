package com.example.shmryandex.presentation

fun Int.toCurrencyString(currency: String = "₽"): String {
    return "%,d".format(this).replace(',', ' ') + " $currency"
}
