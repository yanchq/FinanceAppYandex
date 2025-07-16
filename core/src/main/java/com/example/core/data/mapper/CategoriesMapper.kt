package com.example.core.data.mapper

import com.example.core.domain.entity.Category
import com.example.core.data.network.model.CategoryDto
import com.example.core.data.storage.entity.CategoryDbModel
import javax.inject.Inject

/**
 * Маппер для преобразования DTO категорий в доменные модели.
 * Обеспечивает конвертацию данных между слоями data и domain.
 */
class CategoriesMapper @Inject constructor() {

    fun mapCategoryDtoToDomain(dto: CategoryDto): Category = with(dto) {
        Category(
            id = id,
            name = name,
            emoji = emoji,
            isIncome = isIncome
        )
    }

    fun mapDbToDomain(categoryDb: CategoryDbModel): Category {
        return Category(
            id = categoryDb.id.toLong(),
            name = categoryDb.name,
            isIncome = categoryDb.isIncome,
            emoji = categoryDb.emoji
        )
    }

    fun mapDomainToDb(category: Category): CategoryDbModel {
        return CategoryDbModel(
            id = category.id.toInt(),
            name = category.name,
            isIncome = category.isIncome,
            emoji = category.emoji
        )
    }
}