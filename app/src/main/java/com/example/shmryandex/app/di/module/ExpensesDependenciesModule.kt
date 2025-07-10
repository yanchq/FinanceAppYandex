package com.example.shmryandex.app.di.module

import com.example.expenses.api.ExpensesDependencies
import com.example.shmryandex.app.di.AppComponent
import dagger.Binds
import dagger.Module

@Module
interface ExpensesDependenciesModule {

    @Binds
    fun bindExpensesDependencies(deps: AppComponent): ExpensesDependencies
}