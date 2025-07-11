package com.example.core.di

import com.example.core.data.repository.BaseTransactionsRepositoryImpl
import com.example.core.domain.repository.BaseTransactionsRepository
import dagger.Binds
import dagger.Module

@Module
interface BaseTransactionsRepositoryModule {

    @Binds
    fun bindBaseTransactionsRepository(
        impl: BaseTransactionsRepositoryImpl
    ): BaseTransactionsRepository
}