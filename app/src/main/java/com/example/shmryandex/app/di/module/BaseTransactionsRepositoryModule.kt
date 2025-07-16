package com.example.shmryandex.app.di.module

import com.example.shmryandex.app.data.repository.BaseTransactionsRepositoryImpl
import com.example.core.domain.repository.BaseTransactionsRepository
import com.example.shmryandex.app.di.AppScope
import dagger.Binds
import dagger.Module

@Module
interface BaseTransactionsRepositoryModule {

    @Binds
    @AppScope
    fun bindBaseTransactionsRepository(
        impl: BaseTransactionsRepositoryImpl
    ): BaseTransactionsRepository
}