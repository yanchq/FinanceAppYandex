package com.example.shmryandex.app.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.shmryandex.app.presentation.viewmodel.MainViewModel
import com.example.shmryandex.app.presentation.viewmodel.NetworkViewModel
import com.example.shmryandex.feature.accounts.presentation.addaccount.viewmodel.AddAccountViewModel
import com.example.shmryandex.feature.accounts.presentation.editaccount.viewmodel.EditAccountViewModel
import com.example.shmryandex.feature.accounts.presentation.main.viewmodel.AccountsViewModel
import com.example.shmryandex.feature.categories.presentation.viewmodel.CategoriesViewModel
import com.example.shmryandex.feature.expenses.presentation.viewmodel.ExpensesViewModel
import com.example.shmryandex.feature.history.presentation.viewmodel.HistoryViewModel
import com.example.shmryandex.feature.incomes.presentation.viewmodel.IncomesViewModel
import com.example.shmryandex.feature.splash.viewmodel.SplashViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun bindMainViewModel(mainViewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(NetworkViewModel::class)
    fun bindNetworkViewModel(networkViewModel: NetworkViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel::class)
    fun bindSplashViewModel(splashViewModel: SplashViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ExpensesViewModel::class)
    fun bindExpensesViewModel(expensesViewModel: ExpensesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(IncomesViewModel::class)
    fun bindIncomesViewModel(incomesViewModel: IncomesViewModel): ViewModel

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

    @Binds
    @IntoMap
    @ViewModelKey(CategoriesViewModel::class)
    fun bindCategoriesViewModel(categoriesViewModel: CategoriesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HistoryViewModel::class)
    fun bindHistoryViewModel(historyViewModel: HistoryViewModel): ViewModel
}