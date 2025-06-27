package com.example.shmryandex.feature.categories.domain.repository

import com.example.shmryandex.core.data.network.model.Result
import com.example.shmryandex.feature.categories.domain.entity.Category

/**
 * Репозиторий для работы с категориями транзакций.
 * Определяет методы для получения списка категорий.
 */
interface CategoriesRepository {

    suspend fun getCategoriesList(): Result<List<Category>>
}