package com.example.shmryandex.app.domain.usecase.locale

import com.example.shmryandex.app.domain.repository.OptionsPreferencesRepository
import javax.inject.Inject

class SetLocaleUseCase @Inject constructor(
    private val repository: OptionsPreferencesRepository
) {

    suspend operator fun invoke(locale: String) {
        repository.setLanguage(locale)
    }
}