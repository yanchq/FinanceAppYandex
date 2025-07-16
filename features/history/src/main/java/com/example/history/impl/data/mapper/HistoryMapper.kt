package com.example.history.impl.data.mapper

import com.example.core.data.network.model.TransactionDto
import com.example.core.data.storage.entity.TransactionDbModel
import com.example.core.data.storage.entity.TransactionWithRelations
import com.example.core.utils.convertCurrency
import com.example.core.utils.extractDate
import com.example.core.utils.extractTime
import com.example.history.impl.domain.entity.HistoryItem
import javax.inject.Inject

/**
 * Маппер для преобразования DTO транзакций в доменные объекты истории операций.
 * Используется для конвертации данных, полученных из сети, в формат, 
 * подходящий для отображения в пользовательском интерфейсе.
 */
class HistoryMapper @Inject constructor() {

    /**
     * Преобразует DTO транзакции в элемент истории операций.
     *
     * @param dto DTO объект транзакции, полученный из сети
     * @return [HistoryItem] объект, содержащий информацию о транзакции в формате для UI
     */
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

    fun mapTransactionDbToHistoryItem(transactionDb: TransactionWithRelations): HistoryItem = with(transactionDb) {
        HistoryItem(
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