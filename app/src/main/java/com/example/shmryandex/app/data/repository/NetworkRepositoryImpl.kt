package com.example.shmryandex.app.data.repository

import com.example.shmryandex.app.data.network.NetworkStateReceiver
import com.example.core.domain.repository.NetworkRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

/**
 * Реализация репозитория для мониторинга состояния сети.
 * Управляет регистрацией и отменой регистрации NetworkStateReceiver.
 */
class NetworkRepositoryImpl @Inject constructor(
    private val networkStateReceiver: NetworkStateReceiver
) : NetworkRepository {

    override fun startNetworkMonitoring() {
        networkStateReceiver.register()
    }

    override fun stopNetworkMonitoring() {
        networkStateReceiver.unregister()
    }

    override fun getNetworkStatus(): StateFlow<Boolean> {
        return networkStateReceiver.isNetworkAvailable
    }
} 