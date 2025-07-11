package com.example.core.data.mapper

import android.util.Log
import com.example.core.data.network.model.TransactionDto
import com.example.core.domain.entity.DetailedTransaction
import java.time.Instant
import java.time.ZoneOffset
import javax.inject.Inject

class TransactionMapper @Inject constructor(
    private val accountMapper: AccountMapper,
    private val categoriesMapper: CategoriesMapper
) {

    fun mapTransactionDtoToDetailedTransaction(dto: TransactionDto): DetailedTransaction = with(dto) {
        Log.d("InitDataTest", dto.toString())
        DetailedTransaction(
            id = id,
            name = category.name,
            amount = amount.toDouble(),
            category = categoriesMapper.mapCategoryDtoToDomain(category),
            account = accountMapper.mapAccountDtoToDomain(account),
            comment = comment,
            transactionDate = transactionDate.extractDate(),
            transactionTime = transactionDate.extractTime()
        )
    }

    private fun String.extractDate(): String {
        val instant = Instant.parse(this)
        val dateTime = instant.atZone(ZoneOffset.UTC)
        return dateTime.toLocalDate().toString() // формат yyyy-MM-dd
    }

    private fun String.extractTime(): String {
        val instant = Instant.parse(this)
        val dateTime = instant.atZone(ZoneOffset.UTC)
        return dateTime.toLocalTime().let {
            String.format("%02d:%02d", it.hour, it.minute)
        }
    }
}