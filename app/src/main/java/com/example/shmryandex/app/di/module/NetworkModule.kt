package com.example.shmryandex.app.di.module

import com.example.core.data.network.api.BaseAccountsApi
import com.example.core.data.network.api.TransactionsApi
import com.example.shmryandex.app.data.network.interceptor.BaseInterceptor
import com.example.shmryandex.app.data.network.interceptor.BaseInterceptorImpl
import com.example.shmryandex.app.di.AppScope
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
class NetworkModule {

    @Provides
    @AppScope
    fun provideMoshi(): Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    @Provides
    @AppScope
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
    @AppScope
    fun provideInterceptor(): BaseInterceptor {
        return BaseInterceptorImpl()
    }

    @Provides
    @AppScope
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        moshi: Moshi
    ): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    @Provides
    @AppScope
    fun provideTransactionsApi(retrofit: Retrofit): TransactionsApi =
        retrofit.create(TransactionsApi::class.java)

    @Provides
    @AppScope
    fun provideBaseAccountsApi(retrofit: Retrofit): BaseAccountsApi =
        retrofit.create(BaseAccountsApi::class.java)

    companion object {
        const val BASE_URL = "https://shmr-finance.ru/api/v1/"
    }
}