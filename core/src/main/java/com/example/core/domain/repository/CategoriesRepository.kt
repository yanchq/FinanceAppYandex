package com.example.core.domain.repository

import com.example.core.data.network.model.Result
import com.example.core.domain.entity.Category

/**
 * Репозиторий для работы с категориями транзакций.
 * Определяет методы для получения списка категорий.
 */
interface CategoriesRepository {

    suspend fun getCategoriesList(): List<Category>
}