package com.example.shmryandex.app.di.module

import com.example.shmryandex.app.data.repository.WorkManagerRepositoryImpl
import com.example.shmryandex.app.di.AppScope
import com.example.shmryandex.app.domain.repository.WorkManagerRepository
import dagger.Binds
import dagger.Module

@Module
interface WorkManagerRepositoryModule {

    @AppScope
    @Binds
    fun bindWorkManagerRepository(impl: WorkManagerRepositoryImpl): WorkManagerRepository
}