package com.example.shmryandex.feature.categories.data.repository

import com.example.shmryandex.core.data.network.model.Result
import com.example.shmryandex.feature.categories.data.mapper.CategoriesMapper
import com.example.shmryandex.feature.categories.data.network.api.CategoriesApi
import com.example.shmryandex.feature.categories.domain.entity.Category
import com.example.shmryandex.feature.categories.domain.repository.CategoriesRepository
import javax.inject.Inject

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