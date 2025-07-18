package com.example.shmryandex.app.di.module

import com.example.shmryandex.app.data.repository.SyncPreferencesRepositoryImpl
import com.example.shmryandex.app.di.AppScope
import com.example.shmryandex.app.domain.repository.SyncPreferencesRepository
import dagger.Binds
import dagger.Module

@Module
interface SyncPreferencesRepositoryModule {

    @AppScope
    @Binds
    fun bindSyncPreferencesRepository(impl: SyncPreferencesRepositoryImpl): SyncPreferencesRepository
}