package com.example.core.data.repository

import com.example.core.data.mapper.CategoriesMapper
import com.example.core.data.network.api.CategoriesApi
import com.example.core.data.network.model.Result
import com.example.core.domain.entity.Category
import com.example.core.domain.repository.CategoriesRepository
import javax.inject.Inject

/**
 * Реализация репозитория для работы с категориями.
 * Обеспечивает получение списка категорий через API с последующим маппингом в доменную модель.
 */
class CategoriesRepositoryImpl @Inject constructor(
    private val api: CategoriesApi,
    private val mapper: CategoriesMapper
): CategoriesRepository {

    var categoriesList = listOf<Category>()

    override suspend fun getCategoriesList(): List<Category> {
        if (!categoriesList.isEmpty()) return categoriesList
        else {
            when (val result = loadCategoriesList()) {
                is Result.Success<List<Category>> -> {
                    categoriesList = result.data
                }
                else -> {
                }
            }
            return categoriesList
        }
    }

    private suspend fun loadCategoriesList(): Result<List<Category>> = Result.execute {
        api.getCategories().map { dto ->
            mapper.mapCategoryDtoToDomain(dto)
        }
    }
}