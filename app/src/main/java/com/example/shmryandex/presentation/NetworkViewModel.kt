package com.example.shmryandex.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shmryandex.data.network.NetworkStateReceiver
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NetworkViewModel @Inject constructor(
    private val networkStateReceiver: NetworkStateReceiver
): ViewModel() {

    private val _events = Channel<NetworkEvent>()
    val events = _events.receiveAsFlow()

    init {
        viewModelScope.launch {
            networkStateReceiver.isNetworkAvailable.collect { isAvailable ->
                if (!isAvailable) {
                    _events.send(NetworkEvent.ShowNoConnectionToast)
                }
            }
        }
    }
}