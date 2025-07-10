package com.example.shmryandex.app.di.module

import com.example.categories.api.CategoriesDependencies
import com.example.shmryandex.app.di.AppComponent
import dagger.Binds
import dagger.Module

@Module
interface CategoriesDependenciesModule {

    @Binds
    fun bindCategoriesDependencies(deps: AppComponent): CategoriesDependencies
}