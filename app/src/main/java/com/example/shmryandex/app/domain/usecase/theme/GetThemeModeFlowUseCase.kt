package com.example.shmryandex.app.domain.usecase.theme

import com.example.shmryandex.app.domain.repository.OptionsPreferencesRepository
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class GetThemeModeFlowUseCase @Inject constructor(
    private val repository: OptionsPreferencesRepository
) {

    suspend operator fun invoke(): StateFlow<Boolean> = repository.getThemeModeFlow()
}