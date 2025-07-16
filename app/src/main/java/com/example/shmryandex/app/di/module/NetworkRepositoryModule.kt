package com.example.shmryandex.app.di.module

import com.example.core.data.network.api.BaseAccountsApi
import com.example.core.data.network.api.TransactionsApi
import com.example.shmryandex.app.data.network.interceptor.BaseInterceptor
import com.example.shmryandex.app.data.network.interceptor.BaseInterceptorImpl
import com.example.shmryandex.app.data.repository.NetworkRepositoryImpl
import com.example.shmryandex.app.di.AppScope
import com.example.core.domain.repository.NetworkRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Модуль Hilt для предоставления зависимостей, связанных с мониторингом сети.
 * Связывает интерфейс NetworkRepository с его реализацией.
 */
@Module
interface NetworkRepositoryModule {

    @Binds
    @AppScope
    fun bindNetworkRepository(
        networkRepositoryImpl: NetworkRepositoryImpl
    ): NetworkRepository
} 