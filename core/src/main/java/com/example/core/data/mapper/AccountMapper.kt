package com.example.core.data.mapper

import com.example.core.data.network.model.AccountDto
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
}