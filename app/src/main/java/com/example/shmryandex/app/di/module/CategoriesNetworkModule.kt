package com.example.shmryandex.app.di.module

import com.example.core.data.network.api.CategoriesApi
import com.example.shmryandex.app.di.AppScope
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

/**
 * Модуль Hilt для предоставления сетевых зависимостей модуля категорий.
 * Конфигурирует и предоставляет API интерфейс для работы с категориями.
 */
@Module
class CategoriesNetworkModule {

    @Provides
    @AppScope
    fun provideCategoriesApi(
        retrofit: Retrofit
    ): CategoriesApi {
        return retrofit.create(CategoriesApi::class.java)
    }
}