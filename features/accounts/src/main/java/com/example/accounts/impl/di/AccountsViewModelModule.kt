package com.example.accounts.impl.di

import androidx.lifecycle.ViewModel
import com.example.accounts.impl.presentation.addaccount.viewmodel.AddAccountViewModel
import com.example.accounts.impl.presentation.editaccount.viewmodel.EditAccountViewModel
import com.example.accounts.impl.presentation.main.viewmodel.AccountsViewModel
import com.example.core.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface AccountsViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(AccountsViewModel::class)
    fun bindAccountsViewModel(accountsViewModel: AccountsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AddAccountViewModel::class)
    fun bindAddAccountViewModel(addAccountViewModel: AddAccountViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(EditAccountViewModel::class)
    fun bindEditAccountViewModel(editAccountViewModel: EditAccountViewModel): ViewModel
}