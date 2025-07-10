package com.example.categories.impl.data.repository

import com.example.core.data.network.model.Result
import com.example.core.data.mapper.CategoriesMapper
import com.example.categories.impl.data.network.api.CategoriesApi
import com.example.core.domain.entity.Category
import com.example.categories.impl.domain.repository.CategoriesRepository
import javax.inject.Inject

/**
 * Реализация репозитория для работы с категориями.
 * Обеспечивает получение списка категорий через API с последующим маппингом в доменную модель.
 */
class CategoriesRepositoryImpl @Inject constructor(
    private val api: CategoriesApi,
    private val mapper: CategoriesMapper
): CategoriesRepository {

    override suspend fun getCategoriesList(): Result<List<Category>> = Result.execute {
        api.getCategories().map { dto ->
            mapper.mapCategoryDtoToDomain(dto)
        }
    }
}