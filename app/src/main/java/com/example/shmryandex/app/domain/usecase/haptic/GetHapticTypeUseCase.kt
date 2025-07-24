package com.example.shmryandex.app.domain.usecase.haptic

import com.example.core.data.haptic.HapticType
import com.example.shmryandex.app.domain.repository.OptionsPreferencesRepository
import javax.inject.Inject

class GetHapticTypeUseCase @Inject constructor(
    private val repository: OptionsPreferencesRepository
) {

    suspend operator fun invoke(): HapticType = repository.getHapticType()
}