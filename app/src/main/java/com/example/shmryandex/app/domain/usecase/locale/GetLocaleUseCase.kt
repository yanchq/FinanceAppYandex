package com.example.shmryandex.app.domain.usecase.locale

import com.example.shmryandex.app.domain.repository.OptionsPreferencesRepository
import javax.inject.Inject

class GetLocaleUseCase @Inject constructor(
    private val repository: OptionsPreferencesRepository
) {

    suspend operator fun invoke(): String = repository.getLanguage()
}