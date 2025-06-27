package com.example.shmryandex.app.presentation

sealed class NetworkEvent {
    object ShowNoConnectionToast : NetworkEvent()
}