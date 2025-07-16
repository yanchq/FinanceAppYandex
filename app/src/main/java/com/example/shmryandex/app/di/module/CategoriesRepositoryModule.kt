package com.example.shmryandex.app.di.module

import com.example.shmryandex.app.data.repository.CategoriesRepositoryImpl
import com.example.core.domain.repository.CategoriesRepository
import com.example.shmryandex.app.di.AppScope
import dagger.Binds
import dagger.Module

/**
 * Модуль Hilt для предоставления репозиториев модуля категорий.
 * Связывает интерфейс репозитория категорий с его реализацией.
 */
@Module
interface CategoriesRepositoryModule {

    @Binds
    @AppScope
    fun bindCategoriesRepository(impl: CategoriesRepositoryImpl): CategoriesRepository
}