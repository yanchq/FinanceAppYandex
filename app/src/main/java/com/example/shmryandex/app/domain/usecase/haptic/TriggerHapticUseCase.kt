package com.example.shmryandex.app.domain.usecase.haptic

import com.example.core.data.haptic.HapticManager
import javax.inject.Inject

class TriggerHapticUseCase @Inject constructor(
    private val hapticManager: HapticManager
) {

    suspend operator fun invoke() {
        hapticManager.triggerHaptic()
    }
}