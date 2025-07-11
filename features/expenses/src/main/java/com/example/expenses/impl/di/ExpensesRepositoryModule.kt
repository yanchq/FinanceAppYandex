package com.example.expenses.impl.di

import com.example.expenses.impl.data.repository.ExpensesRepositoryImpl
import com.example.expenses.impl.domain.repository.ExpensesRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

/**
 * Модуль Hilt для предоставления репозиториев модуля расходов.
 * Связывает интерфейс репозитория расходов с его реализацией.
 */
@Module
interface ExpensesRepositoryModule {

    @Binds
    @ExpensesScope
    fun bindExpensesRepository(impl: ExpensesRepositoryImpl): ExpensesRepository
}