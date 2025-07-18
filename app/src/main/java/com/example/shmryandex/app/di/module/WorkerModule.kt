package com.example.shmryandex.app.di.module

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import com.example.shmryandex.app.data.worker.DaggerWorkerFactory
import com.example.shmryandex.app.data.worker.SyncTransactionsWorker
import com.example.shmryandex.app.data.worker.SyncTransactionsWorkerFactory
import com.example.shmryandex.app.di.AppScope
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Inject
import kotlin.reflect.KClass

/**
 * Dagger модуль для настройки WorkManager с dependency injection.
 * Обеспечивает создание воркеров с внедрением зависимостей.
 */
@Module
abstract class WorkerModule {

    @Binds
    @IntoMap
    @WorkerKey(SyncTransactionsWorker::class)
    abstract fun bindSyncTransactionsWorker(factory: SyncTransactionsWorkerFactory): ChildWorkerFactory

    @Binds
    @AppScope
    abstract fun bindWorkerFactory(factory: DaggerWorkerFactory): WorkerFactory
}

/**
 * Интерфейс для создания конкретных воркеров.
 */
interface ChildWorkerFactory {
    fun create(context: Context, params: WorkerParameters): ListenableWorker
}

/**
 * Аннотация для маппинга воркеров в Dagger Map.
 */
@MapKey
@Retention(AnnotationRetention.RUNTIME)
annotation class WorkerKey(val value: KClass<out ListenableWorker>)
