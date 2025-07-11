package com.example.core.di

import com.example.core.data.repository.CategoriesRepositoryImpl
import com.example.core.domain.repository.CategoriesRepository
import dagger.Binds
import dagger.Module

/**
 * Модуль Hilt для предоставления репозиториев модуля категорий.
 * Связывает интерфейс репозитория категорий с его реализацией.
 */
@Module
interface CategoriesRepositoryModule {

    @Binds
    fun bindCategoriesRepository(impl: CategoriesRepositoryImpl): CategoriesRepository
}