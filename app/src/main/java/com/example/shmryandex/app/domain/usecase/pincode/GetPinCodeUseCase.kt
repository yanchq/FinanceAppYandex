package com.example.shmryandex.app.domain.usecase.pincode

import com.example.shmryandex.app.domain.repository.OptionsPreferencesRepository
import javax.inject.Inject

class GetPinCodeUseCase @Inject constructor(
    private val repository: OptionsPreferencesRepository
) {
    suspend operator fun invoke(): Int = repository.getPinCode()
}