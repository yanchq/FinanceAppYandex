package com.example.shmryandex.app.di.module

import com.example.shmryandex.app.data.repository.BaseAccountsRepositoryImpl
import com.example.core.domain.repository.BaseAccountsRepository
import com.example.shmryandex.app.di.AppScope
import dagger.Binds
import dagger.Module

/**
 * Модуль Hilt для предоставления базовых репозиториев.
 * Связывает интерфейсы репозиториев с их реализациями.
 */
@Module
interface BaseRepositoryModule {

    @Binds
    @AppScope
    fun bindAccountsRepository(impl: BaseAccountsRepositoryImpl): BaseAccountsRepository
}