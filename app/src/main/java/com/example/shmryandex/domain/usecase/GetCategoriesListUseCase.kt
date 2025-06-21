package com.example.shmryandex.domain.usecase

import com.example.shmryandex.data.network.Result
import com.example.shmryandex.domain.FinanceRepository
import com.example.shmryandex.domain.entity.Category
import javax.inject.Inject

class GetCategoriesListUseCase @Inject constructor(
    private val repository: FinanceRepository
) {
    suspend operator fun invoke(): Result<List<Category>> = repository.getCategoriesList()
}