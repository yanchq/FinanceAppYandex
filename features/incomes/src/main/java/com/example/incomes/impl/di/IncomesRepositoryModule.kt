package com.example.incomes.impl.di

import com.example.incomes.impl.data.repository.IncomesRepositoryImpl
import com.example.incomes.impl.domain.repository.IncomesRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

/**
 * Dagger Hilt модуль для внедрения зависимостей репозитория доходов.
 * Обеспечивает привязку реализации репозитория к его интерфейсу
 * в рамках жизненного цикла синглтона.
 */
@Module
interface IncomesRepositoryModule {

    @Binds
    @IncomesScope
    fun bindIncomesRepository(impl: IncomesRepositoryImpl): IncomesRepository
}