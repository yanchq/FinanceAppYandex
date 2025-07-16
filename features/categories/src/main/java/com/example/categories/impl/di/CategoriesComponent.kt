package com.example.categories.impl.di

import com.example.categories.api.CategoriesDependencies
import com.example.core.di.ViewModelFactory
import com.example.core.di.ViewModelFactoryScope
import dagger.Component

@CategoriesScope
@ViewModelFactoryScope
@Component(
    dependencies = [
        CategoriesDependencies::class
    ],
    modules = [
        CategoriesViewModelModule::class
    ]
)
interface CategoriesComponent {

    fun viewModelFactory(): ViewModelFactory

    @Component.Factory
    interface Factory {
        fun create(deps: CategoriesDependencies): CategoriesComponent
    }
}