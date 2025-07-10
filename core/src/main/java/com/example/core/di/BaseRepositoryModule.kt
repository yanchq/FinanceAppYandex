package com.example.core.di

import com.example.core.data.repository.BaseAccountsRepositoryImpl
import com.example.core.domain.repository.BaseAccountsRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

/**
 * Модуль Hilt для предоставления базовых репозиториев.
 * Связывает интерфейсы репозиториев с их реализациями.
 */
@Module
interface BaseRepositoryModule {

    @Binds
    @Singleton
    fun bindAccountsRepository(impl: BaseAccountsRepositoryImpl): BaseAccountsRepository
}