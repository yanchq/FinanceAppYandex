package com.example.shmryandex.feature.history.di

import com.example.shmryandex.feature.history.data.repository.HistoryRepositoryImpl
import com.example.shmryandex.feature.history.domain.repository.HistoryRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface RepositoryModule {

    @Binds
    @Singleton
    fun bindHistoryRepository(impl: HistoryRepositoryImpl): HistoryRepository
}