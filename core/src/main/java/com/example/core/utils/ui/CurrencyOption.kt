package com.example.core.utils.ui

data class CurrencyOption(val code: String, val name: String, val symbol: String)

val currencyOptions = listOf(
    CurrencyOption("RUB", "Российский рубль", "₽"),
    CurrencyOption("USD", "Американский доллар", "$"),
    CurrencyOption("EUR", "Евро", "€"),
)