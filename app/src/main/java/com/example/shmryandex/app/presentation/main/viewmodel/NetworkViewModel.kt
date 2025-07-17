package com.example.shmryandex.app.presentation.main.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.usecase.SyncTransactionsUseCase
import com.example.shmryandex.app.domain.usecase.ObserveNetworkStateUseCase
import com.example.shmryandex.app.domain.usecase.StartMonitoringUseCase
import com.example.shmryandex.app.domain.usecase.StopMonitoringUseCase
import com.example.shmryandex.app.domain.usecase.TriggerImmediateSyncUseCase
import com.example.shmryandex.app.presentation.NetworkEvent
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel для управления состоянием сетевого подключения.
 * Отслеживает доступность сети и отправляет события для отображения уведомлений пользователю.
 */
class NetworkViewModel @Inject constructor(
    private val observeNetworkStateUseCase: ObserveNetworkStateUseCase,
    private val startMonitoringUseCase: StartMonitoringUseCase,
    private val stopMonitoringUseCase: StopMonitoringUseCase,
    private val triggerImmediateSyncUseCase: TriggerImmediateSyncUseCase,
) : ViewModel() {

    private val _events = MutableSharedFlow<NetworkEvent>()
    val events: SharedFlow<NetworkEvent> = _events.asSharedFlow()

    init {
        startMonitoringUseCase()
        viewModelScope.launch {
            observeNetworkStateUseCase().collect { isAvailable ->
                if (!isAvailable) {
                    _events.emit(NetworkEvent.ShowNoConnectionToast)
                }
                else {
                    Log.d("SyncTransactionsTest", "Network status: $isAvailable")
                    
                    // Запускаем WorkManager
                    triggerImmediateSyncUseCase()
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        stopMonitoringUseCase()
    }
}