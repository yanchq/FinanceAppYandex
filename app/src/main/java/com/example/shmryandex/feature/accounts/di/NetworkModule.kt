package com.example.shmryandex.feature.accounts.di

import com.example.shmryandex.feature.accounts.data.network.api.AccountsApi
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
    @Singleton
    fun provideAccountsApi(
        retrofit: Retrofit
    ): AccountsApi {
        return retrofit.create(AccountsApi::class.java)
    }
}