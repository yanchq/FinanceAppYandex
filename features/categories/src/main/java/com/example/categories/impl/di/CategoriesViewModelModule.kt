package com.example.categories.impl.di

import androidx.lifecycle.ViewModel
import com.example.categories.impl.presentation.viewmodel.CategoriesViewModel
import com.example.core.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface CategoriesViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(CategoriesViewModel::class)
    fun bindCategoriesViewModel(categoriesViewModel: CategoriesViewModel): ViewModel
}