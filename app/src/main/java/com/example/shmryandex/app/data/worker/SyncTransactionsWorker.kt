package com.example.shmryandex.app.data.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.core.domain.repository.BaseTransactionsRepository
import com.example.core.domain.repository.NetworkRepository
import com.example.shmryandex.app.domain.repository.SyncPreferencesRepository
import com.example.shmryandex.app.domain.repository.SyncPreferencesRepository.Companion.SYNC_ERROR
import com.example.shmryandex.app.domain.repository.SyncPreferencesRepository.Companion.SYNC_SUCCESS
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

/**
 * WorkManager воркер для периодической синхронизации транзакций с сервером.
 * Выполняется в фоновом режиме при наличии интернет-соединения.
 */
class SyncTransactionsWorker @AssistedInject constructor(
    @Assisted private val context: Context,
    @Assisted private val workerParams: WorkerParameters,
    private val baseTransactionsRepository: BaseTransactionsRepository,
    private val networkRepository: NetworkRepository,
    private val syncPreferencesRepository: SyncPreferencesRepository
) : CoroutineWorker(context, workerParams) {


    val currentTimeMillis = System.currentTimeMillis()

    override suspend fun doWork(): Result {
        return try {

            // Проверяем наличие интернет-соединения
            val networkAvailable = networkRepository.getNetworkStatus().value

            if (!networkAvailable) {
                return Result.retry()
            }
            // Выполняем синхронизацию транзакций
            when (val syncResult = baseTransactionsRepository.syncTransactions()) {
                is com.example.core.data.network.model.Result.Success<Unit> -> {
                    syncPreferencesRepository.saveLastSyncInfo(currentTimeMillis, SYNC_SUCCESS)
                    Result.success()
                }
                
                is com.example.core.data.network.model.Result.Error -> {

                    // Определяем, стоит ли повторить попытку
                    val shouldRetry = when {
                        syncResult.exception.message?.contains("network", ignoreCase = true) == true -> true
                        syncResult.exception.message?.contains("timeout", ignoreCase = true) == true -> true
                        runAttemptCount < 3 -> true
                        else -> false
                    }
                    
                    if (shouldRetry) {
                        Result.retry()
                    } else {
                        syncPreferencesRepository.saveLastSyncInfo(currentTimeMillis, SYNC_ERROR)
                        Result.failure()
                    }
                }
                
                is com.example.core.data.network.model.Result.Loading -> {
                    Result.retry()
                }
            }

        } catch (exception: Exception) {
            // Повторяем при неожиданных ошибках, но не более 3 раз
            if (runAttemptCount < 3) {
                Result.retry()
            } else {
                syncPreferencesRepository.saveLastSyncInfo(currentTimeMillis, SYNC_ERROR)
                Result.failure()
            }
        }
    }

    companion object {
        const val WORK_NAME = "sync_transactions_periodic"
    }

    @AssistedFactory
    interface Factory {
        fun create(context: Context, params: WorkerParameters): SyncTransactionsWorker
    }
} 