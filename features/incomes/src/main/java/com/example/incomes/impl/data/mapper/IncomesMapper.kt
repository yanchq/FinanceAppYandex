package com.example.incomes.impl.data.mapper

import com.example.core.data.network.model.TransactionDto
import com.example.core.utils.convertCurrency
import com.example.incomes.impl.domain.entity.Income
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