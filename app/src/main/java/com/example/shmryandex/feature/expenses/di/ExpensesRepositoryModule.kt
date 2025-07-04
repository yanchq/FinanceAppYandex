package com.example.shmryandex.feature.expenses.di

import com.example.shmryandex.feature.expenses.data.repository.ExpensesRepositoryImpl
import com.example.shmryandex.feature.expenses.domain.repository.ExpensesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Модуль Hilt для предоставления репозиториев модуля расходов.
 * Связывает интерфейс репозитория расходов с его реализацией.
 */
@Module
@InstallIn(SingletonComponent::class)
interface ExpensesRepositoryModule {

    @Binds
    @Singleton
    fun bindExpensesRepository(impl: ExpensesRepositoryImpl): ExpensesRepository
}