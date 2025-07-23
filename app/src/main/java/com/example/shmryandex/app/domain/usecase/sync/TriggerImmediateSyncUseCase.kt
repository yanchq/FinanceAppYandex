package com.example.shmryandex.app.domain.usecase.sync

import com.example.shmryandex.app.domain.repository.WorkManagerRepository
import javax.inject.Inject

class TriggerImmediateSyncUseCase @Inject constructor(
    private val repository: WorkManagerRepository
) {

    operator fun invoke() {
        repository.triggerImmediateSync()
    }
}