package com.example.shmryandex.app.domain.repository

import kotlinx.coroutines.flow.StateFlow


interface NetworkRepository {

    fun startNetworkMonitoring()
    fun stopNetworkMonitoring()
    fun getNetworkStatus(): StateFlow<Boolean>
} 