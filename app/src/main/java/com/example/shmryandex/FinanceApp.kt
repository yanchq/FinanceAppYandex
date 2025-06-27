package com.example.shmryandex

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * Основной класс приложения.
 * Инициализирует Hilt для внедрения зависимостей.
 */
@HiltAndroidApp
class FinanceApp: Application() {
}