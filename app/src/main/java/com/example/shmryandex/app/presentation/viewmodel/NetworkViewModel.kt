package com.example.shmryandex.app.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shmryandex.app.domain.usecase.ObserveNetworkStateUseCase
import com.example.shmryandex.app.domain.usecase.StartMonitoringUseCase
import com.example.shmryandex.app.domain.usecase.StopMonitoringUseCase
import com.example.shmryandex.app.presentation.NetworkEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel для управления состоянием сетевого подключения.
 * Отслеживает доступность сети и отправляет события для отображения уведомлений пользователю.
 */
@HiltViewModel
class NetworkViewModel @Inject constructor(
    private val observeNetworkStateUseCase: ObserveNetworkStateUseCase,
    private val startMonitoringUseCase: StartMonitoringUseCase,
    private val stopMonitoringUseCase: StopMonitoringUseCase
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
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        stopMonitoringUseCase()
    }
}