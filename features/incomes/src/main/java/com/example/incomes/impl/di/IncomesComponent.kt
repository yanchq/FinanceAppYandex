package com.example.incomes.impl.di

import com.example.core.di.ViewModelFactory
import com.example.core.di.ViewModelFactoryModule
import com.example.core.di.ViewModelFactoryScope
import com.example.incomes.api.IncomesDependencies
import dagger.Component

@IncomesScope
@ViewModelFactoryScope
@Component(
    dependencies = [
        IncomesDependencies::class
    ],
    modules = [
        ViewModelFactoryModule::class,
        IncomesViewModelModule::class,
        IncomesRepositoryModule::class
    ]
)
interface IncomesComponent {

    fun viewModelFactory(): ViewModelFactory

    @Component.Factory
    interface Factory {
        fun create(deps: IncomesDependencies): IncomesComponent
    }
}