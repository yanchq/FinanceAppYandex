package com.example.shmryandex.domain.usecase

import com.example.shmryandex.domain.FinanceRepository
import com.example.shmryandex.domain.entity.Category
import javax.inject.Inject

class GetCategoriesListUseCase @Inject constructor(
    private val repository: FinanceRepository
) {

    operator fun invoke(): List<Category> {
        return repository.getCategoriesList()
    }
}