package com.example.shmryandex.app.domain.repository

import com.example.core.data.haptic.HapticType
import com.example.core.utils.ui.MainColorType
import kotlinx.coroutines.flow.StateFlow

interface OptionsPreferencesRepository {

    suspend fun getThemeMode(): Boolean

    suspend fun setThemeMode(isDarkMode: Boolean)

    suspend fun getThemeModeFlow(): StateFlow<Boolean>

    suspend fun setHapticEnabledState(isHapticEnabled: Boolean)

    suspend fun getHapticEnabledState(): Boolean

    suspend fun setHapticType(type: HapticType)

    suspend fun getHapticType(): HapticType

    suspend fun setLanguage(language: String)

    suspend fun getLanguage(): String

    suspend fun getLocaleFlow(): StateFlow<String>

    suspend fun getMainThemeColor(): MainColorType

    suspend fun setMainThemeColor(color: MainColorType)

    suspend fun getMainThemeColorFlow(): StateFlow<MainColorType>

    suspend fun getPinCode(): Int

    suspend fun setPinCode(pin: Int)
}