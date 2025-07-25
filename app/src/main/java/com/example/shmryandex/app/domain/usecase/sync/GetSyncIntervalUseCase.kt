package com.example.shmryandex.app.domain.usecase.sync

import com.example.shmryandex.app.domain.repository.SyncPreferencesRepository
import javax.inject.Inject

class GetSyncIntervalUseCase @Inject constructor(
    private val repository: SyncPreferencesRepository
) {

    suspend operator fun invoke(): Long = repository.getSyncInterval()
}