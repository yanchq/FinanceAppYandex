package com.example.shmryandex.feature.categories.data.mapper

import com.example.shmryandex.feature.categories.data.network.model.CategoryDto
import com.example.shmryandex.feature.categories.domain.entity.Category
import javax.inject.Inject

class CategoriesMapper @Inject constructor() {

    fun mapCategoryDtoToDomain(dto: CategoryDto): Category = with(dto) {
        Category(
            id = id,
            name = name,
            emoji = emoji,
            isIncome = isIncome
        )
    }
}