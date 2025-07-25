package com.example.shmryandex.app.data.mapper

import android.util.Log
import com.example.core.data.mapper.AccountMapper
import com.example.core.data.mapper.CategoriesMapper
import com.example.core.data.network.model.CreateTransactionResponse
import com.example.core.data.network.model.TransactionDto
import com.example.core.data.storage.entity.TransactionDbModel
import com.example.core.data.storage.entity.TransactionWithRelations
import com.example.core.domain.entity.DetailedTransaction
import com.example.core.domain.entity.Transaction
import com.example.core.utils.extractDate
import com.example.core.utils.extractTime
import javax.inject.Inject
import kotlin.math.acos
import kotlin.text.toDouble

class TransactionMapper @Inject constructor(
    private val accountMapper: AccountMapper,
    private val categoriesMapper: CategoriesMapper
) {

    fun mapTransactionDtoToDetailedTransaction(dto: TransactionDto): DetailedTransaction =
        with(dto) {
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

    fun mapTransactionDtoToTransaction(dto: TransactionDto): Transaction =
        with(dto) {
            Transaction(
                id = id,
                amount = amount.toDouble(),
                categoryId = category.id.toInt(),
                accountId = account.id,
                comment = comment,
                transactionDate = transactionDate.extractDate(),
                transactionTime = transactionDate.extractTime()
            )
        }

    fun mapTransactionResponseToTransaction(response: CreateTransactionResponse): Transaction =
        with(response) {
            Transaction(
                id = id,
                amount = amount.toDouble(),
                categoryId = categoryId,
                accountId = accountid,
                comment = comment,
                transactionDate = transactionDate.extractDate(),
                transactionTime = transactionDate.extractTime()
            )
        }

    fun mapTransactionResponseToDbModel(response: CreateTransactionResponse): TransactionDbModel =
        with(response) {
            TransactionDbModel(
                id = id.toInt(),
                amount = amount.toDouble(),
                comment = comment,
                transactionDate = transactionDate.extractDate(),
                transactionTime = transactionDate.extractTime(),
                accountId = accountid,
                categoryId = categoryId
            )
        }

    fun mapTransactionDtoToDbModel(dto: TransactionDto): TransactionDbModel =
        with(dto) {
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

    fun mapTransactionDbToTransaction(
        transactionDb: TransactionDbModel
    ): Transaction = with(transactionDb) {
        Transaction(
            id = id.toLong(),
            amount = amount,
            categoryId = id,
            accountId = accountId,
            comment = comment,
            transactionDate = transactionDate,
            transactionTime = transactionTime
        )
    }

    fun mapTransactionWithRelationsDbToTransaction(
        transactionDb: TransactionWithRelations
    ): Transaction = with(transactionDb) {
        Transaction(
            id = transaction.id.toLong(),
            amount = transaction.amount,
            categoryId = category.id,
            accountId = account.id,
            comment = transaction.comment,
            transactionDate = transaction.transactionDate,
            transactionTime = transaction.transactionTime
        )
    }

    fun mapTransactionDbToDetailedTransaction(
        transactionDb: TransactionWithRelations
    ): DetailedTransaction = with(transactionDb) {
        DetailedTransaction(
            id = transaction.id.toLong(),
            name = category.name,
            amount = transaction.amount,
            category = categoriesMapper.mapDbToDomain(category),
            account = accountMapper.mapDbToDomain(account),
            comment = transaction.comment,
            transactionDate = transaction.transactionDate,
            transactionTime = transaction.transactionTime
        )
    }

    fun mapTransactionToDbModel(transaction: Transaction): TransactionDbModel =
        with(transaction) {
            TransactionDbModel(
                id = id.toInt(),
                amount = amount.toDouble(),
                comment = comment,
                transactionDate = transactionDate,
                transactionTime = transactionTime,
                accountId = accountId,
                categoryId = categoryId
            )
        }
}