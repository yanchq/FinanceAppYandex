package com.example.shmryandex.feature.expenses.data.mapper

import com.example.shmryandex.core.data.network.model.TransactionDto
import com.example.shmryandex.core.utils.convertCurrency
import com.example.shmryandex.feature.expenses.domain.entity.Expense
import javax.inject.Inject

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
}