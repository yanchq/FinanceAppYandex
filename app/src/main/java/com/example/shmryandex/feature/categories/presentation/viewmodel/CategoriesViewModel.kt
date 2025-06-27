package com.example.shmryandex.feature.categories.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.shmryandex.core.data.network.model.Result
import com.example.shmryandex.core.presentation.mvi.BaseViewModel
import com.example.shmryandex.feature.categories.domain.entity.Category
import com.example.shmryandex.feature.categories.domain.usecase.GetCategoriesListUseCase
import com.example.shmryandex.feature.categories.presentation.contract.CategoriesUIEffect
import com.example.shmryandex.feature.categories.presentation.contract.CategoriesUIEvent
import com.example.shmryandex.feature.categories.presentation.contract.CategoriesUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
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
            when (val categoriesListResult = getCategoriesListUseCase()) {
                is Result.Error -> {

                }
                Result.Loading -> {

                }
                is Result.Success<List<Category>> -> {
                    setState(currentState.copy(
                        categories = categoriesListResult.data
                    ))
                }
            }
        }
    }
}