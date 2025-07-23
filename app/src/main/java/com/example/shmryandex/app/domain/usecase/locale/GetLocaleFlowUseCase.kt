package com.example.shmryandex.app.domain.usecase.locale

import com.example.shmryandex.app.domain.repository.OptionsPreferencesRepository
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class GetLocaleFlowUseCase @Inject constructor(
    private val repository: OptionsPreferencesRepository
) {

    suspend operator fun invoke(): StateFlow<String> = repository.getLocaleFlow()
}