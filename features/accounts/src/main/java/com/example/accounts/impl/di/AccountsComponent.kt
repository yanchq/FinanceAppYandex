package com.example.accounts.impl.di

import com.example.accounts.api.AccountDependencies
import com.example.core.di.ViewModelFactory
import com.example.core.di.ViewModelFactoryModule
import com.example.core.di.ViewModelFactoryScope
import dagger.Component

@AccountsScope
@ViewModelFactoryScope
@Component(
    dependencies = [
        AccountDependencies::class
    ],
    modules = [
        NetworkModule::class,
        RepositoryModule::class,
        AccountsViewModelModule::class,
        ViewModelFactoryModule::class,
    ]
)
interface AccountsComponent {

    fun viewModelFactory(): ViewModelFactory

    @Component.Factory
    interface Factory {
        fun create(deps: AccountDependencies): AccountsComponent
    }
}