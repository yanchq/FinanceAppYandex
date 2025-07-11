package com.example.expenses.impl.di

import com.example.core.di.ViewModelFactory
import com.example.core.di.ViewModelFactoryModule
import com.example.core.di.ViewModelFactoryScope
import com.example.expenses.api.ExpensesDependencies
import dagger.Component

@ExpensesScope
@ViewModelFactoryScope
@Component(
    dependencies = [
        ExpensesDependencies::class
    ],
    modules = [
        ExpensesRepositoryModule::class,
        ExpensesViewModelModule::class,
        ViewModelFactoryModule::class
    ]
)
interface ExpensesComponent {

    fun viewModelFactory(): ViewModelFactory

    @Component.Factory
    interface Factory {

        fun create(deps: ExpensesDependencies): ExpensesComponent
    }
}