package com.example.shmryandex.presentation

sealed class NetworkEvent {
    object ShowNoConnectionToast : NetworkEvent()
}