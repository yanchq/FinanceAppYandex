package com.example.shmryandex.app.domain.usecase.network

import com.example.core.domain.repository.NetworkRepository
import javax.inject.Inject

/**
 * Use case для остановки мониторинга сетевого подключения.
 * Прекращает отслеживание состояния сети и освобождает связанные ресурсы,
 * используется при завершении работы приложения или необходимости остановки мониторинга.
 */
class StopMonitoringUseCase @Inject constructor(
    private val repository: NetworkRepository
) {

    operator fun invoke() {
        repository.stopNetworkMonitoring()
    }
}