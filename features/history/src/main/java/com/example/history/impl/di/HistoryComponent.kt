package com.example.history.impl.di

import com.example.core.di.ViewModelFactory
import com.example.core.di.ViewModelFactoryModule
import com.example.core.di.ViewModelFactoryScope
import com.example.history.api.HistoryDependencies
import dagger.Component

@HistoryScope
@ViewModelFactoryScope
@Component(
    dependencies = [
        HistoryDependencies::class
    ],
    modules = [
        ViewModelFactoryModule::class,
        RepositoryModule::class,
        HistoryViewModelModule::class
    ]
)
interface HistoryComponent {

    fun viewModelFactory(): ViewModelFactory

    @Component.Factory
    interface Factory {
        fun create(deps: HistoryDependencies): HistoryComponent
    }
}