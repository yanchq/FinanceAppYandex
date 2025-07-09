package com.example.shmryandex.feature.accounts.di

import com.example.shmryandex.feature.accounts.data.repository.AccountsRepositoryImpl
import com.example.shmryandex.feature.accounts.domain.repository.AccountsRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

/**
 * Модуль Hilt для предоставления репозиториев модуля счетов.
 * Связывает интерфейс репозитория счетов с его реализацией.
 */
@Module
interface RepositoryModule {

    @Binds
    @Singleton
    fun bindAccountsRepository(impl: AccountsRepositoryImpl): AccountsRepository
}