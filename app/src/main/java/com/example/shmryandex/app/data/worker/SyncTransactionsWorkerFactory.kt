package com.example.shmryandex.app.data.worker

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerParameters
import com.example.shmryandex.app.di.module.ChildWorkerFactory
import javax.inject.Inject

/**
 * Фабрика для создания SyncTransactionsWorker.
 */
class SyncTransactionsWorkerFactory @Inject constructor(
    private val syncTransactionsWorkerFactory: SyncTransactionsWorker.Factory
) : ChildWorkerFactory {

    override fun create(context: Context, params: WorkerParameters): ListenableWorker {
        return syncTransactionsWorkerFactory.create(context, params)
    }
}
