package com.example.shmryandex

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.work.Configuration
import androidx.work.WorkManager
import com.example.shmryandex.app.di.AppComponent
import com.example.shmryandex.app.di.DaggerAppComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

/**
 * Основной класс приложения.
 * Инициализирует Dagger для внедрения зависимостей и WorkManager.
 */
class FinanceApp: Application() {

    lateinit var appComponent: AppComponent
    
    // CoroutineScope для приложения
    private val applicationScope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

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

            val scheduleSyncUseCase = appComponent.schedulePeriodicSyncUseCase()
            // Запускаем suspend функцию в корутине
            applicationScope.launch {
                scheduleSyncUseCase()
                Log.d("FinanceApp", "Periodic sync scheduled successfully")
            }
    }
}

val Context.appComponent: AppComponent
    get() = when (this) {
        is FinanceApp -> appComponent
        else -> applicationContext.appComponent
    }