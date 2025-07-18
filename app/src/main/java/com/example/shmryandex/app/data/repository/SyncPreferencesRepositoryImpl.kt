package com.example.shmryandex.app.data.repository

import android.content.Context
import com.example.shmryandex.app.domain.repository.SyncPreferencesRepository
import javax.inject.Inject
import androidx.core.content.edit

class SyncPreferencesRepositoryImpl @Inject constructor(
    private val context: Context
): SyncPreferencesRepository {

    private val sharedPreferences = context.getSharedPreferences(
        SYNC_PREF,
        Context.MODE_PRIVATE
    )

    private suspend fun saveLastSyncTime(timestamp: Long) {
        sharedPreferences.edit {
            putLong(KEY_LAST_SYNC_TIME, timestamp)
        }
    }

    private suspend fun getLastSyncTime(): Long? {
        val timestamp = sharedPreferences.getLong(KEY_LAST_SYNC_TIME, -1L)
        return if (timestamp == -1L) null else timestamp
    }

    private suspend fun saveLastSyncStatus(status: String) {
        sharedPreferences.edit {
            putString(KEY_LAST_SYNC_STATUS, status)
        }
    }

    private suspend fun getLastSyncStatus(): String? {
        return sharedPreferences.getString(KEY_LAST_SYNC_STATUS, null)
    }

    override suspend fun saveLastSyncInfo(timestamp: Long, status: String) {
        saveLastSyncTime(timestamp)
        saveLastSyncStatus(status)
    }

    override suspend fun getLastSyncInfo(): Pair<Long?, String?> {
        val time = getLastSyncTime()
        val status = getLastSyncStatus()
        return Pair(time, status)
    }

    companion object {
        private const val SYNC_PREF = "sync_preferences"
        private const val KEY_LAST_SYNC_TIME = "last_sync_time"
        private const val KEY_LAST_SYNC_STATUS = "last_sync_status"
    }
}