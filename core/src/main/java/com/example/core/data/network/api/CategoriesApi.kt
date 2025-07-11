package com.example.core.data.network.api

import com.example.core.data.network.model.CategoryDto
import retrofit2.http.GET

/**
 * API интерфейс для работы с категориями.
 * Определяет эндпоинты для получения списка категорий.
 */
interface CategoriesApi {

    @GET("categories")
    suspend fun getCategories(): List<CategoryDto>
}