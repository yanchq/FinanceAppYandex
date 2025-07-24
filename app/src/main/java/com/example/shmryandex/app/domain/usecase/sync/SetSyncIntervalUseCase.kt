package com.example.shmryandex.app.domain.usecase.sync

import com.example.shmryandex.app.domain.repository.SyncPreferencesRepository
import javax.inject.Inject

class SetSyncIntervalUseCase @Inject constructor(
    private val repository: SyncPreferencesRepository
) {

    suspend operator fun invoke(interval: Long) {
        repository.saveSyncInterval(interval)
    }
}