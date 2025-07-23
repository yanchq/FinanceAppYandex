package com.example.shmryandex.app.domain.usecase.theme

import com.example.shmryandex.app.domain.repository.OptionsPreferencesRepository
import javax.inject.Inject

class GetThemeModeUseCase @Inject constructor(
    private val repository: OptionsPreferencesRepository
) {

    suspend operator fun invoke(): Boolean = repository.getThemeMode()
}