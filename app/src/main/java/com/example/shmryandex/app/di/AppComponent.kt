package com.example.shmryandex.app.di

import android.content.Context
import com.example.shmryandex.app.presentation.MainActivity
import com.example.shmryandex.core.di.BaseRepositoryModule
import com.example.shmryandex.feature.categories.di.NetworkModule
import com.example.shmryandex.feature.categories.di.RepositoryModule
import com.example.shmryandex.feature.expenses.di.ExpensesRepositoryModule
import com.example.shmryandex.feature.incomes.di.IncomesRepositoryModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        NetworkModule::class,
        com.example.shmryandex.app.di.NetworkModule::class,
        com.example.shmryandex.core.di.NetworkModule::class,
        com.example.shmryandex.feature.accounts.di.NetworkModule::class,
        RepositoryModule::class,
        com.example.shmryandex.feature.history.di.RepositoryModule::class,
        com.example.shmryandex.feature.accounts.di.RepositoryModule::class,
        BaseRepositoryModule::class,
        ExpensesRepositoryModule::class,
        IncomesRepositoryModule::class,
        ViewModelModule::class
    ]
)
interface AppComponent {

    fun viewModelFactory(): ViewModelFactory

    fun inject(mainActivity: MainActivity)

    @Component.Factory
    interface Factory {

        fun create(@BindsInstance context: Context): AppComponent
    }
}