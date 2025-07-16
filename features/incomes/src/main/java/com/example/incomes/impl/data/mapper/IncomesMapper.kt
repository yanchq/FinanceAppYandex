package com.example.incomes.impl.data.mapper

import com.example.core.data.network.model.TransactionDto
import com.example.core.data.storage.entity.TransactionDbModel
import com.example.core.data.storage.entity.TransactionWithRelations
import com.example.core.utils.convertCurrency
import com.example.core.utils.extractDate
import com.example.core.utils.extractTime
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

    fun mapTransactionDbToIncome(transactionDb: TransactionWithRelations): Income = with(transactionDb) {
        Income(
            id = transaction.id.toLong(),
            name = category.name,
            emoji = category.emoji,
            amount = transaction.amount.toDouble(),
            currency = account.currency,
            comment = transaction.comment,
            createdAt = transaction.transactionDate
        )
    }

    fun mapTransactionDtoToDbModel(transactionDto: TransactionDto): TransactionDbModel = with(transactionDto) {
        TransactionDbModel(
            id = id.toInt(),
            amount = amount.toDouble(),
            comment = comment,
            transactionDate = transactionDate.extractDate(),
            transactionTime = transactionDate.extractTime(),
            accountId = account.id,
            categoryId = category.id.toInt()
        )
    }
}