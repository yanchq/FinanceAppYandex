package com.example.shmryandex

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.work.Configuration
import androidx.work.WorkManager
import com.example.shmryandex.app.di.AppComponent
import com.example.shmryandex.app.di.DaggerAppComponent

/**
 * Основной класс приложения.
 * Инициализирует Dagger для внедрения зависимостей и WorkManager.
 */
class FinanceApp: Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        
        // Сначала создаем компонент
        appComponent = DaggerAppComponent.factory().create(this)
        
        // Затем инициализируем WorkManager с нашей фабрикой
        initializeWorkManager()
        
        // Планируем периодическую синхронизацию
        schedulePeriodicSync()
    }

    private fun initializeWorkManager() {
        try {
            val configuration = Configuration.Builder()
                .setWorkerFactory(appComponent.workerFactory())
                .setMinimumLoggingLevel(Log.DEBUG)
                .build()
            
            WorkManager.initialize(this, configuration)
            Log.d("FinanceApp", "WorkManager initialized successfully with DaggerWorkerFactory")
        } catch (e: Exception) {
            Log.e("FinanceApp", "Failed to initialize WorkManager", e)
        }
    }

    private fun schedulePeriodicSync() {
        try {
            val scheduleSyncUseCase = appComponent.schedulePeriodicSyncUseCase()
            scheduleSyncUseCase()
            Log.d("FinanceApp", "Periodic sync scheduled successfully")
        } catch (e: Exception) {
            Log.e("FinanceApp", "Failed to schedule periodic sync", e)
        }
    }
}

val Context.appComponent: AppComponent
    get() = when (this) {
        is FinanceApp -> appComponent
        else -> applicationContext.appComponent
    }