package com.example.shmryandex.app.domain.repository

import kotlinx.coroutines.flow.StateFlow

/**
 * Репозиторий для работы с сетевым подключением.
 * Определяет методы для управления мониторингом сети и получения текущего статуса подключения.
 */
interface NetworkRepository {

    fun startNetworkMonitoring()
    fun stopNetworkMonitoring()
    fun getNetworkStatus(): StateFlow<Boolean>
} 