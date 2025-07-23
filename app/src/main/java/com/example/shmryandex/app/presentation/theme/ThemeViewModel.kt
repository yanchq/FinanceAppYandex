package com.example.shmryandex.app.presentation.theme

import android.content.Context
import android.content.res.Configuration
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.utils.ui.MainColorType
import com.example.shmryandex.app.domain.usecase.locale.GetLocaleFlowUseCase
import com.example.shmryandex.app.domain.usecase.theme.GetMainThemeColorFlowUseCase
import com.example.shmryandex.app.domain.usecase.theme.GetThemeModeFlowUseCase
import com.example.shmryandex.app.domain.usecase.theme.SetThemeModeUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject

/**
 * ViewModel для управления темой приложения.
 * Отвечает за получение и установку темы, а также предоставление состояния темы через StateFlow.
 */
class ThemeViewModel @Inject constructor(
    private val getThemeModeFlowUseCase: GetThemeModeFlowUseCase,
    private val setThemeModeUseCase: SetThemeModeUseCase,
    private val getLocaleFlowUseCase: GetLocaleFlowUseCase,
    private val getMainThemeColorFlowUseCase: GetMainThemeColorFlowUseCase
) : ViewModel() {

    private val _isDarkTheme = MutableStateFlow(false)
    val isDarkTheme: StateFlow<Boolean> = _isDarkTheme.asStateFlow()

    private val _locale = MutableStateFlow("ru")
    val locale: StateFlow<String> = _locale.asStateFlow()

    private val _mainColor = MutableStateFlow(MainColorType.getDefault())
    val mainColor: StateFlow<MainColorType> = _mainColor.asStateFlow()

    init {
        observeThemeMode()
        observeLocale()
        observeMainThemeColor()
    }

    fun setThemeMode(isDark: Boolean) {
        Log.d("ThemeModeTest", "Is darkMode = $isDark")
        viewModelScope.launch(Dispatchers.IO) {
            _isDarkTheme.value = isDark
            setThemeModeUseCase(isDark)
        }
    }

    private fun observeThemeMode() {
        viewModelScope.launch(Dispatchers.IO) {
            getThemeModeFlowUseCase().collect { isDarkTheme ->
                _isDarkTheme.value = isDarkTheme
            }
        }
    }

    private fun observeLocale() {
        viewModelScope.launch(Dispatchers.IO) {
            getLocaleFlowUseCase().collect { locale ->
                _locale.value = locale
            }
        }
    }

     fun updateLocaleContext(context: Context): Context {

        val newLocale = Locale(locale.value)
        Locale.setDefault(newLocale)

        val config = Configuration(context.resources.configuration)
        config.setLocale(newLocale)
        config.setLayoutDirection(newLocale)

        return context.createConfigurationContext(config)
    }

    fun observeMainThemeColor() {
        viewModelScope.launch(Dispatchers.IO) {
            getMainThemeColorFlowUseCase().collect { color ->
                _mainColor.value = color
            }
        }
    }
} 