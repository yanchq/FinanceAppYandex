package com.example.shmryandex.app.di.module

import com.example.shmryandex.app.data.repository.NetworkRepositoryImpl
import com.example.shmryandex.app.domain.repository.NetworkRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

/**
 * Модуль Hilt для предоставления зависимостей, связанных с мониторингом сети.
 * Связывает интерфейс NetworkRepository с его реализацией.
 */
@Module
interface NetworkModule {

    @Binds
    @Singleton
    fun bindNetworkRepository(
        networkRepositoryImpl: NetworkRepositoryImpl
    ): NetworkRepository
} 