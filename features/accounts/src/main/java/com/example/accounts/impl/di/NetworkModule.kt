package com.example.accounts.impl.di

import com.example.accounts.impl.data.network.api.AccountsApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Модуль Hilt для предоставления сетевых зависимостей модуля счетов.
 * Конфигурирует и предоставляет API интерфейс для работы со счетами.
 */
@Module
class NetworkModule {

    @Provides
    @AccountsScope
    fun provideAccountsApi(
        retrofit: Retrofit
    ): AccountsApi {
        return retrofit.create(AccountsApi::class.java)
    }
}