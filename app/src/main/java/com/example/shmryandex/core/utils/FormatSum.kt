package com.example.shmryandex.core.utils

/**
 * Набор функций расширения для форматирования денежных сумм.
 * Преобразует числовые значения в строковое представление с разделителями
 * разрядов и символом валюты.
 */

/**
 * Форматирует целое число в строку с разделителями разрядов и символом валюты.
 * @param currency символ валюты для добавления к сумме
 * @return отформатированная строка с суммой и валютой
 */
fun Int.toCurrencyString(currency: String): String {
    return "%,d".format(this).replace(',', ' ') + " $currency"
}

/**
 * Форматирует число с плавающей точкой в строку с разделителями разрядов и символом валюты.
 * Если число целое, отбрасывает десятичную часть.
 * @param currency символ валюты для добавления к сумме
 * @return отформатированная строка с суммой и валютой
 */
fun Double.toCurrencyString(currency: String): String {
    return if (this % 1 == 0.0) {
        "%,d".format(this.toLong()).replace(',', ' ') + " $currency"
    } else {
        "%,.2f".format(this).replace(',', ' ') + " $currency"
    }
}

