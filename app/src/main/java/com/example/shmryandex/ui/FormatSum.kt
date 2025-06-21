package com.example.shmryandex.ui

fun Int.toCurrencyString(currency: String): String {
    return "%,d".format(this).replace(',', ' ') + " $currency"
}

fun Double.toCurrencyString(currency: String): String {
    return if (this % 1 == 0.0) {
        "%,d".format(this.toLong()).replace(',', ' ') + " $currency"
    } else {
        "%,.2f".format(this).replace(',', ' ') + " $currency"
    }
}

