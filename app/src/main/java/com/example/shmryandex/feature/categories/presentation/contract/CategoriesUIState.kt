package com.example.shmryandex.feature.categories.presentation.contract

import com.example.shmryandex.core.presentation.mvi.UIState
import com.example.shmryandex.feature.categories.domain.entity.Category

/**
 * Класс состояния UI экрана категорий.
 * Содержит список категорий для отображения на экране.
 */
data class CategoriesUIState(
    val categories: List<Category> = emptyList()
): UIState
