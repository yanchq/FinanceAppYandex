package com.example.shmryandex.app.domain.repository

/**
 * Интерфейс репозитория для управления WorkManager задачами.
 * Предоставляет методы для мониторинга состояния синхронизации.
 */
interface WorkManagerRepository {

    fun schedulePeriodicSync()

    fun triggerImmediateSync()
} 