package com.example.shmryandex.data.mapper

import com.example.shmryandex.data.network.model.AccountDto
import com.example.shmryandex.data.network.model.CategoryDto
import com.example.shmryandex.data.network.model.TransactionDto
import com.example.shmryandex.domain.entity.Account
import com.example.shmryandex.domain.entity.Category
import com.example.shmryandex.domain.entity.Expense
import com.example.shmryandex.domain.entity.Income
import javax.inject.Inject

class FinanceMapper @Inject constructor() {

    fun mapAccountDtoToDomain(dto: AccountDto): Account = with(dto) {
        Account(
            id = id,
            name = name,
            balance = balance.toDouble(),
            currency = currency.convertCurrency()
        )
    }

    fun mapCategoryDtoToDomain(dto: CategoryDto): Category = with(dto) {
        Category(
            id = id,
            name = name,
            emoji = emoji,
            isIncome = isIncome
        )
    }

    fun mapTransactionDtoToExpense(dto: TransactionDto): Expense = with(dto) {
        Expense(
            id = id,
            category = mapCategoryDtoToDomain(category),
            amount = amount.toDouble(),
            currency = dto.account.currency.convertCurrency(),
            comment = comment,
            createdAt = transactionDate.substring(0, 10)
        )
    }

    fun mapTransactionDtoToIncome(dto: TransactionDto): Income = with(dto) {
        Income(
            id = id,
            category = mapCategoryDtoToDomain(category),
            amount = amount.toDouble(),
            currency = dto.account.currency.convertCurrency(),
            comment = comment,
            createdAt = transactionDate.substring(0, 10)
        )
    }

    private fun String.convertCurrency(): String {
        when (this) {
            "RUB" -> return "₽"
            "USD" -> return "$"
            "EUR" -> return "€"
            else -> return "₽"
        }
    }
} 