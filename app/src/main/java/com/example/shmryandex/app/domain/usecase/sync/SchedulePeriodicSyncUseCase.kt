package com.example.shmryandex.app.domain.usecase.sync

import com.example.shmryandex.app.domain.repository.WorkManagerRepository
import javax.inject.Inject

class SchedulePeriodicSyncUseCase @Inject constructor(
    private val repository: WorkManagerRepository
) {

    suspend operator fun invoke() {
        repository.schedulePeriodicSync()
    }
}