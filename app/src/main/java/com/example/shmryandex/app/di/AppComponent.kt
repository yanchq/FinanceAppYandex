package com.example.shmryandex.app.di

import android.content.Context
import com.example.accounts.api.AccountDependencies
import com.example.categories.api.CategoriesDependencies
import com.example.shmryandex.app.presentation.MainActivity
import com.example.core.di.BaseRepositoryModule
import com.example.core.di.BaseTransactionsRepositoryModule
import com.example.core.di.CategoriesNetworkModule
import com.example.core.di.CategoriesRepositoryModule
import com.example.core.di.ViewModelFactory
import com.example.core.di.ViewModelFactoryModule
import com.example.core.di.ViewModelFactoryScope
import com.example.expenses.api.ExpensesDependencies
import com.example.history.api.HistoryDependencies
import com.example.incomes.api.IncomesDependencies
import com.example.shmryandex.app.di.module.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@ViewModelFactoryScope
@Component(
    modules = [
        com.example.shmryandex.app.di.module.NetworkModule::class,
        com.example.core.di.NetworkModule::class,
        com.example.history.impl.di.RepositoryModule::class,
        BaseRepositoryModule::class,
        ViewModelModule::class,
        ViewModelFactoryModule::class,
        CategoriesRepositoryModule::class,
        CategoriesNetworkModule::class,
        BaseTransactionsRepositoryModule::class
    ]
)
interface AppComponent :
    AccountDependencies, ExpensesDependencies,
    IncomesDependencies, HistoryDependencies,
    CategoriesDependencies
{

    fun viewModelFactory(): ViewModelFactory

    fun inject(mainActivity: MainActivity)

    @Component.Factory
    interface Factory {

        fun create(@BindsInstance context: Context): AppComponent
    }
}