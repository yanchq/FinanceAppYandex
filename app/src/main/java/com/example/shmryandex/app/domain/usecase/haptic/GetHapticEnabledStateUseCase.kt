package com.example.shmryandex.app.domain.usecase.haptic

import com.example.shmryandex.app.domain.repository.OptionsPreferencesRepository
import javax.inject.Inject

class GetHapticEnabledStateUseCase @Inject constructor(
    private val repository: OptionsPreferencesRepository
) {
    suspend operator fun invoke(): Boolean = repository.getHapticEnabledState()
}