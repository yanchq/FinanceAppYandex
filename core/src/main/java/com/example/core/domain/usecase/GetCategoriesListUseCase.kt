package com.example.core.domain.usecase

import com.example.core.data.network.model.Result
import com.example.core.domain.entity.Category
import com.example.core.domain.repository.CategoriesRepository
import javax.inject.Inject

/**
 * Use case для получения списка категорий.
 * Возвращает Result с списком категорий в случае успеха или ошибку в случае неудачи.
 */
class GetCategoriesListUseCase @Inject constructor(
    private val repository: CategoriesRepository
) {

    suspend operator fun invoke(): List<Category> {
        return repository.getCategoriesList()
    }
}