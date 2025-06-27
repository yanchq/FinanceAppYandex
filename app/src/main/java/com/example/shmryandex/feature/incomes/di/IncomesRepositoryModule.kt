package com.example.shmryandex.feature.incomes.di

import com.example.shmryandex.feature.incomes.data.repository.IncomesRepositoryImpl
import com.example.shmryandex.feature.incomes.domain.repository.IncomesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Dagger Hilt модуль для внедрения зависимостей репозитория доходов.
 * Обеспечивает привязку реализации репозитория к его интерфейсу
 * в рамках жизненного цикла синглтона.
 */
@Module
@InstallIn(SingletonComponent::class)
interface IncomesRepositoryModule {

    @Binds
    @Singleton
    fun bindIncomesRepository(impl: IncomesRepositoryImpl): IncomesRepository
}