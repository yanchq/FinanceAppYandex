package com.example.shmryandex.app.di.module

import com.example.accounts.api.AccountDependencies
import com.example.shmryandex.app.di.AppComponent
import dagger.Binds
import dagger.Module

@Module
interface AccountsDependenciesModule {

    @Binds
    fun bindAccountsDependencies(deps: AppComponent): AccountDependencies
}