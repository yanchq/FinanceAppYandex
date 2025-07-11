package com.example.core.di

import com.example.core.data.network.api.CategoriesApi
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
    fun provideCategoriesApi(
        retrofit: Retrofit
    ): CategoriesApi {
        return retrofit.create(CategoriesApi::class.java)
    }
}