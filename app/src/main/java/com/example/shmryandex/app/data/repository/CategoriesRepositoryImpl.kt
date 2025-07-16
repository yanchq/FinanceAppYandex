package com.example.shmryandex.app.data.repository

import android.util.Log
import com.example.core.data.mapper.CategoriesMapper
import com.example.core.data.network.api.CategoriesApi
import com.example.core.data.network.model.Result
import com.example.core.domain.entity.Category
import com.example.core.domain.repository.CategoriesRepository
import com.example.core.data.storage.dao.CategoriesDao
import com.example.core.domain.repository.NetworkRepository
import javax.inject.Inject

/**
 * Реализация репозитория для работы с категориями.
 * Обеспечивает получение списка категорий через API с последующим маппингом в доменную модель.
 */
class CategoriesRepositoryImpl @Inject constructor(
    private val api: CategoriesApi,
    private val mapper: CategoriesMapper,
    private val categoriesDao: CategoriesDao,
    private val networkRepository: NetworkRepository
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
                    Log.d("LoadCategoriesTest", result.toString())
                }
            }
            return categoriesList
        }
    }

    private suspend fun loadCategoriesList(): Result<List<Category>> = Result.execute {
        if (networkRepository.getNetworkStatus().value) {
            loadCategoriesFromNetwork()
        }
        else {
            loadCategoriesFromDb()
        }
    }

    private suspend fun loadCategoriesFromNetwork(): List<Category> {
        val categoriesDto = api.getCategories()
        val categoriesDomain = categoriesDto.map { categoryDto ->
            mapper.mapCategoryDtoToDomain(categoryDto)
        }

        categoriesDao.insertCategories(categoriesDomain.map { mapper.mapDomainToDb(it) })

        return categoriesDomain
    }

    private suspend fun loadCategoriesFromDb(): List<Category> {
        val dbCategories = categoriesDao.getAllCategories()

        return dbCategories.map { dbCategory ->
            mapper.mapDbToDomain(dbCategory)
        }
    }
}