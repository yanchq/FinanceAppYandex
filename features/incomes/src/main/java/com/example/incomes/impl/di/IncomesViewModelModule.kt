package com.example.incomes.impl.di

import androidx.lifecycle.ViewModel
import com.example.core.di.ViewModelKey
import com.example.incomes.impl.presentation.viewmodel.IncomesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface IncomesViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(IncomesViewModel::class)
    fun bindIncomesViewModel(incomesViewModel: IncomesViewModel): ViewModel
}