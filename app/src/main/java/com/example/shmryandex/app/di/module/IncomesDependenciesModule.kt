package com.example.shmryandex.app.di.module

import com.example.incomes.api.IncomesDependencies
import com.example.shmryandex.app.di.AppComponent
import dagger.Binds
import dagger.Module

@Module
interface IncomesDependenciesModule {

    @Binds
    fun bindIncomesDependencies(deps: AppComponent): IncomesDependencies
}