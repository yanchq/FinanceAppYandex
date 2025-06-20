package com.example.shmryandex.data

import android.util.Log
import com.example.shmryandex.data.mapper.FinanceMapper
import com.example.shmryandex.data.network.Result
import com.example.shmryandex.data.network.api.FinanceApi
import com.example.shmryandex.data.network.model.CreateAccountRequest
import com.example.shmryandex.data.network.model.TransactionDto
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
    private var todayTransactionsList = emptyList<TransactionDto>()

    override fun getExpensesList(): List<Expense> {
        return todayTransactionsList.filter { !it.category.isIncome }
            .map { dto ->
                mapper.mapTransactionDtoToExpense(dto)
            }
    }

    override fun getIncomesList(): List<Income> {
        return todayTransactionsList.filter { it.category.isIncome }
            .map { dto ->
                mapper.mapTransactionDtoToIncome(dto)
            }
    }

    override suspend fun loadTodayTransactions(): Result<Unit> = Result.execute {

        todayTransactionsList = coroutineScope {
            accountsList.map { account ->
                async {
                    api.getTransactionsByAccountPeriod(
                        accountId = account.id,
                        startDate = today,
                        endDate = today
                    )
                }
            }
        }.awaitAll().flatten()
    }

    override suspend fun loadExpensesByPeriod(
        startDate: String,
        endDate: String
    ): Result<List<Expense>> = Result.execute {
        val expensesList = coroutineScope {
            accountsList.map { account ->
                async {
                    api.getTransactionsByAccountPeriod(
                        accountId = account.id,
                        startDate = today,
                        endDate = today
                    ).filter { !it.category.isIncome }
                        .map { dto ->
                            mapper.mapTransactionDtoToExpense(dto)
                        }
                }
            }
        }.awaitAll().flatten()
        expensesList
    }

    override suspend fun loadIncomesByPeriod(
        startDate: String,
        endDate: String
    ): Result<List<Income>> = Result.execute {
        val incomeList = coroutineScope {
            accountsList.map { account ->
                async {
                    api.getTransactionsByAccountPeriod(
                        accountId = account.id,
                        startDate = today,
                        endDate = today
                    ).filter { it.category.isIncome }
                        .map { dto ->
                            mapper.mapTransactionDtoToIncome(dto)
                        }
                }
            }
        }.awaitAll().flatten()
        incomeList
    }

    override fun getAccountsList(): List<Account> = accountsList

    override suspend fun loadAccountsList() = Result.execute {

        accountsList = api.getAccountsList().map { dto ->
            mapper.mapAccountDtoToDomain(dto)
        }
    }

    override suspend fun createAccount(
        name: String,
        balance: String,
        currency: String
    ): Result<Unit> {
        val result = Result.execute {
            api.createAccount(
                CreateAccountRequest(
                    name = name,
                    balance = balance,
                    currency = currency
                )
            )
        }
        loadAccountsList()
        return result
    }

    override suspend fun deleteAccount(id: Int): Result<Unit> {
        val result = Result.execute {
            api.deleteAccount(id)
        }
        loadAccountsList()
        return result
    }

    override suspend fun getCategoriesList(): Result<List<Category>> = Result.execute {
        api.getCategories().map { dto ->
            mapper.mapCategoryDtoToDomain(dto)
        }
    }
}