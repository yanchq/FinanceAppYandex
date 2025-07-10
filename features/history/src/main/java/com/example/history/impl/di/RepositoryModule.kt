package com.example.history.impl.di

import com.example.history.impl.data.repository.HistoryRepositoryImpl
import com.example.history.impl.domain.repository.HistoryRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface RepositoryModule {

    @Binds
    @HistoryScope
    fun bindHistoryRepository(impl: HistoryRepositoryImpl): HistoryRepository
}