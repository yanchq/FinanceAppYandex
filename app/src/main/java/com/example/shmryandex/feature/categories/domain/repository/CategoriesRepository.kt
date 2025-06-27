package com.example.shmryandex.feature.categories.domain.repository

import com.example.shmryandex.core.data.network.model.Result
import com.example.shmryandex.feature.categories.domain.entity.Category

interface CategoriesRepository {

    suspend fun getCategoriesList(): Result<List<Category>>
}