package com.example.core.di

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface ViewModelFactoryModule {

    @Binds
    @ViewModelFactoryScope
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}