package com.example.shmryandex.app.domain.usecase

import com.example.shmryandex.app.domain.repository.NetworkRepository
import javax.inject.Inject

class StartMonitoringUseCase @Inject constructor(
    private val repository: NetworkRepository
) {

    operator fun invoke() {
        repository.startNetworkMonitoring()
    }
}