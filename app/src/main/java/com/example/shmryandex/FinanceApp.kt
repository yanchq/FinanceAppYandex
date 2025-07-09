package com.example.shmryandex

import android.app.Application
import android.content.Context
import com.example.shmryandex.app.di.AppComponent
import com.example.shmryandex.app.di.DaggerAppComponent

/**
 * Основной класс приложения.
 * Инициализирует Hilt для внедрения зависимостей.
 */

class FinanceApp: Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.factory().create(this)
    }
}

val Context.appComponent: AppComponent
    get() = when (this) {
        is FinanceApp -> appComponent
        else -> applicationContext.appComponent
    }