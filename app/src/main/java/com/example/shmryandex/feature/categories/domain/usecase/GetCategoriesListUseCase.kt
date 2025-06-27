package com.example.shmryandex.feature.categories.domain.usecase

import com.example.shmryandex.core.data.network.model.Result
import com.example.shmryandex.feature.categories.domain.entity.Category
import com.example.shmryandex.feature.categories.domain.repository.CategoriesRepository
import javax.inject.Inject

class GetCategoriesListUseCase @Inject constructor(
    private val repository: CategoriesRepository
) {

    suspend operator fun invoke(): Result<List<Category>> {
        return repository.getCategoriesList()
    }
}