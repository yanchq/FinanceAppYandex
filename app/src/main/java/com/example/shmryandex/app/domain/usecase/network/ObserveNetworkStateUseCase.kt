package com.example.shmryandex.app.domain.usecase.network

import com.example.core.domain.repository.NetworkRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Use case для наблюдения за состоянием сетевого подключения.
 * Возвращает Flow с текущим статусом подключения.
 */
class ObserveNetworkStateUseCase @Inject constructor(
    private val networkRepository: NetworkRepository
) {
    operator fun invoke(): Flow<Boolean> = networkRepository.getNetworkStatus()
} 