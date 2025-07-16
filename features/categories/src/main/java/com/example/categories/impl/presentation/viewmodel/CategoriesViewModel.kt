package com.example.categories.impl.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.core.data.network.model.Result
import com.example.core.presentation.mvi.BaseViewModel
import com.example.core.domain.entity.Category
import com.example.core.domain.usecase.GetCategoriesListUseCase
import com.example.categories.impl.presentation.contract.CategoriesUIEffect
import com.example.categories.impl.presentation.contract.CategoriesUIEvent
import com.example.categories.impl.presentation.contract.CategoriesUIState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel для экрана категорий транзакций.
 * Управляет состоянием списка категорий, загружает данные при инициализации
 * и обрабатывает пользовательские события.
 */

class CategoriesViewModel @Inject constructor(
    private val getCategoriesListUseCase: GetCategoriesListUseCase
): BaseViewModel<CategoriesUIEvent, CategoriesUIState, CategoriesUIEffect>
    (CategoriesUIState()) {

        init {
            getCategoriesList()
        }

    override fun onEvent(event: CategoriesUIEvent) {

    }

    private fun getCategoriesList() {
        viewModelScope.launch(Dispatchers.IO) {
            val categoriesListResult = getCategoriesListUseCase()
            setState(currentState.copy(
                categories = categoriesListResult
            ))
        }
    }
}