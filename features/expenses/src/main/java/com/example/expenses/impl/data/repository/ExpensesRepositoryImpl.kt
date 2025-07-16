package com.example.expenses.impl.data.repository

import com.example.core.data.network.api.TransactionsApi
import com.example.core.data.network.model.Result
import com.example.core.data.storage.dao.TransactionsDao
import com.example.core.domain.entity.Account
import com.example.core.domain.repository.NetworkRepository
import com.example.expenses.impl.data.mapper.ExpensesMapper
import com.example.expenses.impl.domain.entity.Expense
import com.example.expenses.impl.domain.repository.ExpensesRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import java.time.LocalDate
import javax.inject.Inject

/**
 * Реализация репозитория для работы с расходами.
 * Получает транзакции по всем счетам за текущий день,
 * фильтрует расходные операции и преобразует их в доменную модель.
 */
class ExpensesRepositoryImpl @Inject constructor(
    private val mapper: ExpensesMapper,
    private val api: TransactionsApi,
    private val transactionsDao: TransactionsDao,
    private val networkRepository: NetworkRepository
) : ExpensesRepository {

    private val today = LocalDate.now().toString()

    override suspend fun getExpensesList(accounts: List<Account>): Result<List<Expense>> =
        Result.execute {
            if (networkRepository.getNetworkStatus().value) {
                loadExpensesFromNetwork(accounts)
            }
            else {
                loadExpensesFromDb()
            }
        }

    private suspend fun loadExpensesFromNetwork(accounts: List<Account>): List<Expense> {
        val expensesDto = coroutineScope {
            accounts.map { account ->
                async {
                    api.getTransactionsByAccountPeriod(
                        accountId = account.id,
                        startDate = today,
                        endDate = today
                    )
                }
            }
        }.awaitAll().flatten()


        val expensesDomain = expensesDto
            .filter { !it.category.isIncome }
            .map { dto ->
                mapper.mapTransactionDtoToExpense(dto)
            }

        val expensesDb = expensesDto
            .filter { !it.category.isIncome }
            .map { dto ->
                mapper.mapTransactionDtoToDbModel(dto)
            }

        transactionsDao.insertTransactionsList(expensesDb)

        return expensesDomain
    }

    private suspend fun loadExpensesFromDb(): List<Expense> {
        return transactionsDao.getTransactionsByPeriod(
            startDate = today,
            endDate = today
        ).filter { !it.category.isIncome }
            .map { db ->
                mapper.mapTransactionDbToExpense(db)
            }
    }
}