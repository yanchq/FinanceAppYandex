package com.example.shmryandex.app.domain.usecase.theme

import com.example.core.utils.ui.MainColorType
import com.example.shmryandex.app.domain.repository.OptionsPreferencesRepository
import javax.inject.Inject

class GetMainThemeColorUseCase @Inject constructor(
    private val repository: OptionsPreferencesRepository
) {
    suspend operator fun invoke(): MainColorType = repository.getMainThemeColor()
}