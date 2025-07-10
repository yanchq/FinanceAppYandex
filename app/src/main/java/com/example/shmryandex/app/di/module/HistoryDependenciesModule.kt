package com.example.shmryandex.app.di.module

import com.example.history.api.HistoryDependencies
import com.example.shmryandex.app.di.AppComponent
import dagger.Binds
import dagger.Module

@Module
interface HistoryDependenciesModule {

    @Binds
    fun bindHistoryDependencies(deps: AppComponent): HistoryDependencies
}