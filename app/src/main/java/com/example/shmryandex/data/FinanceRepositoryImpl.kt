package com.example.shmryandex.data

import android.util.Log
import com.example.shmryandex.data.mapper.FinanceMapper
import com.example.shmryandex.data.network.Result
import com.example.shmryandex.data.network.api.FinanceApi
import com.example.shmryandex.domain.entity.Expense
import com.example.shmryandex.domain.FinanceRepository
import com.example.shmryandex.domain.entity.Account
import com.example.shmryandex.domain.entity.Category
import com.example.shmryandex.domain.entity.Income
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import java.time.LocalDate
import javax.inject.Inject

class FinanceRepositoryImpl @Inject constructor(
    private val api: FinanceApi,
    private val mapper: FinanceMapper
) : FinanceRepository {

    private val today = LocalDate.now().toString()
    private var accountsList = emptyList<Account>()

    override suspend fun getExpensesList(): Result<List<Expense>> = Result.execute {

        val expensesList = coroutineScope {
            accountsList.map { account ->
                async {
                    api.getTransactionsByAccountPeriod(
                        accountId = account.id,
                        startDate = today,
                        endDate = today
                    )
                        .filter { !it.category.isIncome }
                        .map { dto ->
                            mapper.mapTransactionDtoToExpense(dto)
                        }
                }
            }
        }.awaitAll().flatten()
        expensesList
    }

    override suspend fun getIncomesList(): Result<List<Income>> = Result.execute {
        api.getTransactionsByAccountPeriod(
            accountId = 53,
            startDate = today,
            endDate = today
        )
            .filter { it.category.isIncome }
            .map { dto ->
                mapper.mapTransactionDtoToIncome(dto)
            }
    }

    override fun getAccountsList(): List<Account> = accountsList

    override suspend fun loadAccountsList() = Result.execute{
        accountsList = api.getAccountsList().map { dto ->
            mapper.mapAccountDtoToDomain(dto)
        }
    }

    override suspend fun getCategoriesList(): Result<List<Category>> = Result.execute {
        api.getCategories().map {
            dto ->
            mapper.mapCategoryDtoToDomain(dto)
        }
    }
}