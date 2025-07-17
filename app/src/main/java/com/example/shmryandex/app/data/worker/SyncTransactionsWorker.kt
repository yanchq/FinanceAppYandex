package com.example.shmryandex.app.data.worker

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.core.domain.repository.BaseTransactionsRepository
import com.example.core.domain.repository.NetworkRepository
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
    private val networkRepository: NetworkRepository
) : CoroutineWorker(context, workerParams) {

    companion object {
        const val TAG = "SyncTransactionsTest"
        const val WORK_NAME = "sync_transactions_periodic"
    }

    override suspend fun doWork(): Result {
        Log.d(TAG, "========================================")
        Log.d(TAG, "Starting transaction synchronization...")
        Log.d(TAG, "Worker ID: ${id}")
        Log.d(TAG, "Run attempt: $runAttemptCount")
        Log.d(TAG, "Input data: $inputData")
        Log.d(TAG, "Tags: ${tags.joinToString(", ")}")
        Log.d(TAG, "========================================")

        return try {
            // Проверяем наличие интернет-соединения
            val networkAvailable = networkRepository.getNetworkStatus().value
            Log.d(TAG, "Network status check: $networkAvailable")
            
            if (!networkAvailable) {
                Log.w(TAG, "No internet connection available. Skipping sync.")
                return Result.retry()
            }

            Log.d(TAG, "Starting synchronization process...")

            // Выполняем синхронизацию транзакций
            when (val syncResult = baseTransactionsRepository.syncTransactions()) {
                is com.example.core.data.network.model.Result.Success<Unit> -> {
                    Log.d(TAG, "Transaction synchronization completed successfully")
                    Result.success()
                }
                
                is com.example.core.data.network.model.Result.Error -> {
                    Log.e(TAG, "Transaction synchronization failed", syncResult.exception)
                    
                    // Определяем, стоит ли повторить попытку
                    val shouldRetry = when {
                        syncResult.exception.message?.contains("network", ignoreCase = true) == true -> true
                        syncResult.exception.message?.contains("timeout", ignoreCase = true) == true -> true
                        runAttemptCount < 3 -> true
                        else -> false
                    }
                    
                    if (shouldRetry) {
                        Log.d(TAG, "Scheduling retry for transaction sync (attempt ${runAttemptCount + 1})")
                        Result.retry()
                    } else {
                        Log.e(TAG, "Max retry attempts reached or non-retryable error. Failing sync.")
                        Result.failure()
                    }
                }
                
                is com.example.core.data.network.model.Result.Loading -> {
                    Log.w(TAG, "Unexpected loading state during sync")
                    Result.retry()
                }
            }

        } catch (exception: Exception) {
            Log.e(TAG, "Unexpected error during transaction synchronization", exception)
            
            // Повторяем при неожиданных ошибках, но не более 3 раз
            if (runAttemptCount < 3) {
                Result.retry()
            } else {
                Result.failure()
            }
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(context: Context, params: WorkerParameters): SyncTransactionsWorker
    }
} 