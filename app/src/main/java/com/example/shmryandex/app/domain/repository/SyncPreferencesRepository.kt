package com.example.shmryandex.app.domain.repository

interface SyncPreferencesRepository {
    suspend fun saveLastSyncInfo(timestamp: Long, status: String)

    suspend fun getLastSyncInfo(): Pair<Long?, String?>

    suspend fun saveSyncInterval(interval: Long)

    suspend fun getSyncInterval(): Long

    companion object {
        const val SYNC_SUCCESS = "Успешно синхронизованно"
        const val SYNC_ERROR = "Ошибка при синхронизации"
    }
}