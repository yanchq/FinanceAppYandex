package com.example.shmryandex.app.di.module

import com.example.core.data.haptic.HapticManager
import com.example.shmryandex.app.data.haptic.HapticManagerImpl
import com.example.shmryandex.app.di.AppScope
import dagger.Binds
import dagger.Module

@Module
interface HapticModule {

    @AppScope
    @Binds
    fun bindHapticManager(impl: HapticManagerImpl): HapticManager
}