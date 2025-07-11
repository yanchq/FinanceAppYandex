package com.example.expenses.impl.di

import androidx.lifecycle.ViewModel
import com.example.core.di.ViewModelKey
import com.example.expenses.impl.presentation.addexpense.viewmodel.AddExpenseViewModel
import com.example.expenses.impl.presentation.editexpense.viewmodel.EditExpenseViewModel
import com.example.expenses.impl.presentation.main.viewmodel.ExpensesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ExpensesViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ExpensesViewModel::class)
    fun bindExpensesViewModel(expensesViewModel: ExpensesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AddExpenseViewModel::class)
    fun bindAddExpenseViewModel(addExpenseViewModel: AddExpenseViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(EditExpenseViewModel::class)
    fun bindEditExpenseViewModel(editExpenseViewModel: EditExpenseViewModel): ViewModel
}