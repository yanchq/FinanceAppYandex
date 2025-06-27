package com.example.shmryandex.app.presentation

/**
 * Sealed класс для событий, связанных с состоянием сети.
 * Используется для уведомления пользователя о проблемах с подключением.
 */
sealed class NetworkEvent {
    object ShowNoConnectionToast : NetworkEvent()
}