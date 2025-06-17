package com.example.shmryandex.presentation.screens.categories

import com.example.shmryandex.domain.entity.Category

data class CategoriesUiState(
    val categories: List<Category> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
) {
}