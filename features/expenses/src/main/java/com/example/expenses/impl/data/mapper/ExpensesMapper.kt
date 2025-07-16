package com.example.expenses.impl.data.mapper

import com.example.core.data.network.model.TransactionDto
import com.example.core.data.storage.entity.TransactionDbModel
import com.example.core.data.storage.entity.TransactionWithRelations
import com.example.core.utils.convertCurrency
import com.example.core.utils.extractDate
import com.example.core.utils.extractTime
import com.example.expenses.impl.domain.entity.Expense
import javax.inject.Inject
import kotlin.text.toDouble

/**
 * Маппер для преобразования транзакций в расходы.
 * Конвертирует сетевые DTO объекты транзакций в доменные модели расходов,
 * выполняя необходимые преобразования валют и форматирование данных.
 */
class ExpensesMapper @Inject constructor() {

    fun mapTransactionDtoToExpense(dto: TransactionDto): Expense = with(dto) {
        Expense(
            id = id,
            name = dto.category.name,
            emoji = dto.category.emoji,
            amount = amount.toDouble(),
            currency = dto.account.currency.convertCurrency(),
            comment = comment,
            createdAt = transactionDate
        )
    }

    fun mapTransactionDbToExpense(transactionDb: TransactionWithRelations): Expense = with(transactionDb) {
        Expense(
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