package com.example.shmryandex.app.presentation.options.changelocale

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shmryandex.app.domain.usecase.locale.GetLocaleFlowUseCase
import com.example.shmryandex.app.domain.usecase.locale.SetLocaleUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class ChangeLocaleViewModel @Inject constructor(
    private val getLocaleFlowUseCase: GetLocaleFlowUseCase,
    private val setLocaleUseCase: SetLocaleUseCase
): ViewModel() {

    private val _locale = MutableStateFlow("ru")
    val locale = _locale.asStateFlow()

    init {
        observeLocale()
    }

    private fun observeLocale() {
        viewModelScope.launch(Dispatchers.IO) {
            getLocaleFlowUseCase().collect { locale ->
                Log.d("ChangeLocaleTest", "Locale changed to: $locale")
                _locale.value = locale
            }
        }
    }

    fun setLocale(locale: String) {
        viewModelScope.launch(Dispatchers.IO) {
            Log.d("ChangeLocaleTest", "Try to change locale to: $locale")
            setLocaleUseCase(locale)
        }
    }
}