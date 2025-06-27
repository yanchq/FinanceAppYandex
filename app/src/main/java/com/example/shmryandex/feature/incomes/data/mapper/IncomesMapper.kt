package com.example.shmryandex.feature.incomes.data.mapper

import com.example.shmryandex.core.data.network.model.TransactionDto
import com.example.shmryandex.core.utils.convertCurrency
import com.example.shmryandex.feature.incomes.domain.entity.Income
import javax.inject.Inject

/**
 * Маппер для преобразования транзакций в доходы.
 * Конвертирует сетевые DTO объекты транзакций в доменные модели доходов,
 * обеспечивая корректное преобразование валют и форматирование дат.
 */
class IncomesMapper @Inject constructor() {

    fun mapTransactionDtoToIncome(dto: TransactionDto): Income = with(dto) {
        Income(
            id = id,
            name = dto.category.name,
            emoji = dto.category.emoji,
            amount = amount.toDouble(),
            currency = dto.account.currency.convertCurrency(),
            comment = comment,
            createdAt = transactionDate.substring(0, 10)
        )
    }
}