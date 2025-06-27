package com.example.shmryandex.feature.categories.di

import com.example.shmryandex.feature.categories.data.network.api.CategoriesApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Модуль Hilt для предоставления сетевых зависимостей модуля категорий.
 * Конфигурирует и предоставляет API интерфейс для работы с категориями.
 */
@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideCategoriesApi(
        retrofit: Retrofit
    ): CategoriesApi {
        return retrofit.create(CategoriesApi::class.java)
    }
}