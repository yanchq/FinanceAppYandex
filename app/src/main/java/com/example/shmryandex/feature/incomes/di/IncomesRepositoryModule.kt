package com.example.shmryandex.feature.incomes.di

import com.example.shmryandex.feature.incomes.data.repository.IncomesRepositoryImpl
import com.example.shmryandex.feature.incomes.domain.repository.IncomesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface IncomesRepositoryModule {

    @Binds
    @Singleton
    fun bindIncomesRepository(impl: IncomesRepositoryImpl): IncomesRepository
}