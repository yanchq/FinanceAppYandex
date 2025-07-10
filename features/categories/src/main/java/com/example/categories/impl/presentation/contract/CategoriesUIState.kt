package com.example.categories.impl.presentation.contract

import com.example.core.presentation.mvi.UIState
import com.example.core.domain.entity.Category

/**
 * Класс состояния UI экрана категорий.
 * Содержит список категорий для отображения на экране.
 */
data class CategoriesUIState(
    val categories: List<Category> = emptyList()
): UIState
