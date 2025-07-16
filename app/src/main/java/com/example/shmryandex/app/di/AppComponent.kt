package com.example.shmryandex.app.di

import android.content.Context
import com.example.accounts.api.AccountDependencies
import com.example.categories.api.CategoriesDependencies
import com.example.shmryandex.app.presentation.MainActivity
import com.example.shmryandex.app.di.module.BaseRepositoryModule
import com.example.shmryandex.app.di.module.BaseTransactionsRepositoryModule
import com.example.shmryandex.app.di.module.CategoriesNetworkModule
import com.example.shmryandex.app.di.module.CategoriesRepositoryModule
import com.example.core.di.ViewModelFactory
import com.example.core.di.ViewModelFactoryModule
import com.example.core.di.ViewModelFactoryScope
import com.example.expenses.api.ExpensesDependencies
import com.example.history.api.HistoryDependencies
import com.example.incomes.api.IncomesDependencies
import com.example.shmryandex.app.di.module.AppDatabaseModule
import com.example.shmryandex.app.di.module.NetworkModule
import com.example.shmryandex.app.di.module.NetworkRepositoryModule
import com.example.shmryandex.app.di.module.ViewModelModule
import dagger.BindsInstance
import dagger.Component

@AppScope
@ViewModelFactoryScope
@Component(
    modules = [
        NetworkRepositoryModule::class,
        com.example.history.impl.di.RepositoryModule::class,
        BaseRepositoryModule::class,
        ViewModelModule::class,
        ViewModelFactoryModule::class,
        CategoriesRepositoryModule::class,
        CategoriesNetworkModule::class,
        BaseTransactionsRepositoryModule::class,
        NetworkModule::class,
        AppDatabaseModule::class
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