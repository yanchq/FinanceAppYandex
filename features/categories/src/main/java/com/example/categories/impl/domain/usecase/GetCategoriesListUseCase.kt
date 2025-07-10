package com.example.categories.impl.domain.usecase

import com.example.core.data.network.model.Result
import com.example.core.domain.entity.Category
import com.example.categories.impl.domain.repository.CategoriesRepository
import javax.inject.Inject

/**
 * Use case для получения списка категорий.
 * Возвращает Result с списком категорий в случае успеха или ошибку в случае неудачи.
 */
class GetCategoriesListUseCase @Inject constructor(
    private val repository: CategoriesRepository
) {

    suspend operator fun invoke(): Result<List<Category>> {
        return repository.getCategoriesList()
    }
}