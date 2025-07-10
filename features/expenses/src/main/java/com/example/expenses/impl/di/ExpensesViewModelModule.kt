package com.example.expenses.impl.di

import androidx.lifecycle.ViewModel
import com.example.core.di.ViewModelKey
import com.example.expenses.impl.presentation.viewmodel.ExpensesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ExpensesViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ExpensesViewModel::class)
    fun bindExpensesViewModel(expensesViewModel: ExpensesViewModel): ViewModel
}