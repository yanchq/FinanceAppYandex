package com.example.shmryandex.app.domain.usecase

import com.example.core.domain.repository.NetworkRepository
import javax.inject.Inject

/**
 * Use case для запуска мониторинга сетевого подключения.
 * Инициирует отслеживание состояния сети через репозиторий,
 * позволяя приложению реагировать на изменения подключения.
 */
class StartMonitoringUseCase @Inject constructor(
    private val repository: NetworkRepository
) {

    operator fun invoke() {
        repository.startNetworkMonitoring()
    }
}