package com.example.shmryandex.app.domain.usecase.haptic

import com.example.core.data.haptic.HapticType
import com.example.shmryandex.app.domain.repository.OptionsPreferencesRepository
import javax.inject.Inject

class SetHapticTypeUseCase @Inject constructor(
    private val repository: OptionsPreferencesRepository
) {
    suspend operator fun invoke(hapticType: HapticType) {
        repository.setHapticType(hapticType)
    }
}