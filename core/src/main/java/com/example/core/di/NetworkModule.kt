package com.example.core.di

import com.example.core.data.network.api.BaseAccountsApi
import com.example.core.data.network.api.TransactionsApi
import com.example.core.data.network.interceptor.BaseInterceptor
import com.example.core.data.network.interceptor.BaseInterceptorImpl
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

/**
 * Модуль Hilt для предоставления сетевых зависимостей.
 * Конфигурирует и предоставляет экземпляры Retrofit, OkHttpClient, Moshi и базовые API интерфейсы.
 */
@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideMoshi(): Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    @Provides
    @Singleton
    fun provideOkHttpClient(
        baseInterceptor: BaseInterceptor
    ): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        return OkHttpClient.Builder()
            .addInterceptor(baseInterceptor)
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideInterceptor(): BaseInterceptor {
        return BaseInterceptorImpl()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        moshi: Moshi
    ): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    @Provides
    @Singleton
    fun provideTransactionsApi(retrofit: Retrofit): TransactionsApi =
        retrofit.create(TransactionsApi::class.java)

    @Provides
    @Singleton
    fun provideBaseAccountsApi(retrofit: Retrofit): BaseAccountsApi =
        retrofit.create(BaseAccountsApi::class.java)

    companion object {
        const val BASE_URL = "https://shmr-finance.ru/api/v1/"
    }
}

