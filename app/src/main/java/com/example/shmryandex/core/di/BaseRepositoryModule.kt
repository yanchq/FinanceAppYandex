package com.example.shmryandex.core.di

import com.example.shmryandex.core.data.repository.BaseAccountsRepositoryImpl
import com.example.shmryandex.core.domain.repository.BaseAccountsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Модуль Hilt для предоставления базовых репозиториев.
 * Связывает интерфейсы репозиториев с их реализациями.
 */
@Module
@InstallIn(SingletonComponent::class)
interface BaseRepositoryModule {

    @Binds
    @Singleton
    fun bindAccountsRepository(impl: BaseAccountsRepositoryImpl): BaseAccountsRepository
}