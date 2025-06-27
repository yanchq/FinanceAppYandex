package com.example.shmryandex.feature.categories.data.network.api

import com.example.shmryandex.feature.categories.data.network.model.CategoryDto
import retrofit2.http.GET

interface CategoriesApi {

    @GET("categories")
    suspend fun getCategories(): List<CategoryDto>
}