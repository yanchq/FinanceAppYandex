package com.example.shmryandex.app.domain.usecase.haptic

import com.example.shmryandex.app.domain.repository.OptionsPreferencesRepository
import javax.inject.Inject

class SetHapticEnabledStateUseCase @Inject constructor(
    private val repository: OptionsPreferencesRepository
) {
    suspend operator fun invoke(state: Boolean) = repository.setHapticEnabledState(state)
}