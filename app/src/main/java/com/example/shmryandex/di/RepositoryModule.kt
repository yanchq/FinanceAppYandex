package com.example.shmryandex.di

import com.example.shmryandex.data.FinanceRepositoryImpl
import com.example.shmryandex.domain.FinanceRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindRepository(impl: FinanceRepositoryImpl): FinanceRepository
}