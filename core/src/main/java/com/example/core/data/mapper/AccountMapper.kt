package com.example.core.data.mapper

import com.example.core.data.network.model.AccountDto
import com.example.core.data.storage.entity.AccountDbModel
import com.example.core.domain.entity.Account
import com.example.core.utils.convertCurrency
import javax.inject.Inject

/**
 * Маппер для преобразования объектов счета между DTO и доменной моделью.
 * Обеспечивает конвертацию данных из формата API в формат, используемый в бизнес-логике приложения.
 */
class AccountMapper @Inject constructor() {

    fun mapAccountDtoToDomain(dto: AccountDto): Account = with(dto) {
        Account(
            id = id,
            name = name,
            balance = balance.toDouble(),
            currency = currency.convertCurrency()
        )
    }

    fun mapDomainToDb(account: Account): AccountDbModel {
        return AccountDbModel(
            id = account.id,
            name = account.name,
            balance = account.balance,
            currency = account.currency,
        )
    }

    fun mapDbToDomain(dbModel: AccountDbModel): Account {
        return Account(
            id = dbModel.id,
            name = dbModel.name,
            balance = dbModel.balance,
            currency = dbModel.currency
        )
    }

    fun mapDbToDomainList(dbList: List<AccountDbModel>): List<Account> {
        return dbList.map { mapDbToDomain(it) }
    }
}