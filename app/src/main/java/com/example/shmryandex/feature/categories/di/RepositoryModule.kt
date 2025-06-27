package com.example.shmryandex.feature.categories.di

import com.example.shmryandex.feature.categories.data.repository.CategoriesRepositoryImpl
import com.example.shmryandex.feature.categories.domain.repository.CategoriesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    @Singleton
    fun bindCategoriesRepository(impl: CategoriesRepositoryImpl): CategoriesRepository
}