package com.example.shmryandex.app.data.repository

import android.content.Context
import android.util.Log
import androidx.work.BackoffPolicy
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.example.shmryandex.app.domain.repository.WorkManagerRepository
import com.example.shmryandex.app.data.worker.SyncTransactionsWorker
import com.example.shmryandex.app.domain.repository.SyncPreferencesRepository
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Реализация репозитория для управления WorkManager задачами.
 * Предоставляет информацию о состоянии синхронизации и управляет задачами.
 */
class WorkManagerRepositoryImpl @Inject constructor(
    private val context: Context,
    private val syncPreferencesRepository: SyncPreferencesRepository
) : WorkManagerRepository {

    private val workManager = WorkManager.getInstance(context)

    override suspend fun schedulePeriodicSync() {

        val syncRepeatInterval = syncPreferencesRepository.getSyncInterval()

        Log.d("ServerSyncTest", "sync interval: $syncRepeatInterval hours")

        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .setRequiresBatteryNotLow(false)
            .setRequiresCharging(false)
            .build()

        val syncWorkRequest = PeriodicWorkRequestBuilder<SyncTransactionsWorker>(
            repeatInterval = syncRepeatInterval,
            repeatIntervalTimeUnit = TimeUnit.HOURS
        )
            .setConstraints(constraints)
            .setBackoffCriteria(
                BackoffPolicy.EXPONENTIAL,
                INITIAL_BACKOFF_DELAY_MINUTES,
                TimeUnit.MINUTES
            )
            .addTag("sync_transactions")
            .build()

        workManager.enqueueUniquePeriodicWork(
            SyncTransactionsWorker.WORK_NAME,
            ExistingPeriodicWorkPolicy.KEEP, // Сохраняем существующую работу если она уже запланирована
            syncWorkRequest
        )
    }

    override fun triggerImmediateSync() {
        // Временно убираем требование сети для отладки
        val constraints = Constraints.Builder()
            // .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val immediateSyncRequest = OneTimeWorkRequestBuilder<SyncTransactionsWorker>()
            .setConstraints(constraints)
            .setBackoffCriteria(
                BackoffPolicy.LINEAR,
                5,
                TimeUnit.MINUTES
            )
            .addTag("sync_transactions_immediate")
            .build()

        workManager.enqueue(immediateSyncRequest)
    }


    companion object {
        const val SYNC_INTERVAL_HOURS = 4L
        const val INITIAL_BACKOFF_DELAY_MINUTES = 15L
    }
} 