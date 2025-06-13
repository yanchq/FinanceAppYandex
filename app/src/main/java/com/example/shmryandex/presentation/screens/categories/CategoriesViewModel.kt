package com.example.shmryandex.presentation.screens.categories

import androidx.lifecycle.ViewModel
import com.example.shmryandex.domain.usecase.GetCategoriesListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(
    private val getCategoriesListUseCase: GetCategoriesListUseCase
): ViewModel() {

    private val _uiState = MutableStateFlow(CategoriesUiState())
    val uiState: StateFlow<CategoriesUiState>
        get() = _uiState

    init {
        getCategories()
    }

    private fun getCategories() {
        val list = getCategoriesListUseCase()
        _uiState.value = CategoriesUiState(list)
    }
}