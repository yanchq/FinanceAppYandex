package com.example.shmryandex.core.data.mapper

import com.example.shmryandex.core.data.network.model.AccountDto
import com.example.shmryandex.core.domain.entity.Account
import com.example.shmryandex.core.utils.convertCurrency
import javax.inject.Inject

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