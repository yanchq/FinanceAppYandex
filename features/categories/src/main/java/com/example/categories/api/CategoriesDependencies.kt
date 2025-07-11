package com.example.categories.api

import com.example.core.domain.repository.CategoriesRepository
import retrofit2.Retrofit

interface CategoriesDependencies {

    val categoriesRepository: CategoriesRepository
}