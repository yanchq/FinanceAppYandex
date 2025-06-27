package com.example.shmryandex.feature.history.data.mapper

import com.example.shmryandex.core.data.network.model.TransactionDto
import com.example.shmryandex.core.utils.convertCurrency
import com.example.shmryandex.feature.expenses.domain.entity.Expense
import com.example.shmryandex.feature.history.domain.entity.HistoryItem
import javax.inject.Inject

class HistoryMapper @Inject constructor() {

    fun mapTransactionDtoToHistoryItem(dto: TransactionDto): HistoryItem = with(dto) {
        HistoryItem(
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