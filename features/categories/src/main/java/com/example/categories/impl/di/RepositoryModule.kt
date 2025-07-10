package com.example.categories.impl.di

import com.example.categories.impl.data.repository.CategoriesRepositoryImpl
import com.example.categories.impl.domain.repository.CategoriesRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

/**
 * Модуль Hilt для предоставления репозиториев модуля категорий.
 * Связывает интерфейс репозитория категорий с его реализацией.
 */
@Module
interface RepositoryModule {

    @Binds
    @CategoriesScope
    fun bindCategoriesRepository(impl: CategoriesRepositoryImpl): CategoriesRepository
}