package com.example.accounts.impl.di

import com.example.accounts.impl.data.repository.AccountsRepositoryImpl
import com.example.accounts.impl.domain.repository.AccountsRepository
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
    @AccountsScope
    fun bindAccountsRepository(impl: AccountsRepositoryImpl): AccountsRepository
}