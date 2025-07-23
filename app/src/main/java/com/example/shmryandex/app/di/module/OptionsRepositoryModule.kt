package com.example.shmryandex.app.di.module

import com.example.shmryandex.app.data.repository.OptionsPreferencesRepositoryImpl
import com.example.shmryandex.app.di.AppScope
import com.example.shmryandex.app.domain.repository.OptionsPreferencesRepository
import dagger.Binds
import dagger.Module

@Module
interface OptionsRepositoryModule {

    @AppScope
    @Binds
    fun bindOptionsPreferencesRepository(
        impl: OptionsPreferencesRepositoryImpl
    ): OptionsPreferencesRepository
}