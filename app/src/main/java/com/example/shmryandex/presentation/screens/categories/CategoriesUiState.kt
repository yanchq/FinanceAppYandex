package com.example.shmryandex.presentation.screens.categories

import com.example.shmryandex.domain.entity.Category

data class CategoriesUiState(
    val categoriesList: List<Category> = emptyList()
) {
}