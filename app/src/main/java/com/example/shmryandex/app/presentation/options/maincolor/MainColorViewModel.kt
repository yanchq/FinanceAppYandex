package com.example.shmryandex.app.presentation.options.maincolor

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.utils.ui.MainColorType
import com.example.shmryandex.app.domain.usecase.theme.GetMainThemeColorFlowUseCase
import com.example.shmryandex.app.domain.usecase.theme.SetMainThemeColorUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainColorViewModel @Inject constructor(
    private val getMainThemeColorFlowUseCase: GetMainThemeColorFlowUseCase,
    private val setMainThemeColorUseCase: SetMainThemeColorUseCase
): ViewModel() {

    private val _mainColor = MutableStateFlow(MainColorType.getDefault())
    val mainColor = _mainColor.asStateFlow()

    init {
        observeMainColor()
    }

    private fun observeMainColor() {
        viewModelScope.launch(Dispatchers.IO) {
            getMainThemeColorFlowUseCase().collect { mainColor ->
                _mainColor.value = mainColor
            }
        }
    }

    fun changeMainColor(color: MainColorType) {
        viewModelScope.launch(Dispatchers.IO) {
            setMainThemeColorUseCase(color)
        }
    }
}