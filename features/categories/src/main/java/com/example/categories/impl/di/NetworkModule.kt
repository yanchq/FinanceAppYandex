package com.example.categories.impl.di

import com.example.categories.impl.data.network.api.CategoriesApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Модуль Hilt для предоставления сетевых зависимостей модуля категорий.
 * Конфигурирует и предоставляет API интерфейс для работы с категориями.
 */
@Module
class NetworkModule {

    @Provides
    @CategoriesScope
    fun provideCategoriesApi(
        retrofit: Retrofit
    ): CategoriesApi {
        return retrofit.create(CategoriesApi::class.java)
    }
}