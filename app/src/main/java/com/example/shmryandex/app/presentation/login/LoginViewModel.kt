package com.example.shmryandex.app.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shmryandex.app.domain.usecase.pincode.GetPinCodeUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val getPinCodeUseCase: GetPinCodeUseCase
): ViewModel() {

    private var pin = 0

    init {
        getPinCode()
    }

    private fun getPinCode() {
        viewModelScope.launch(Dispatchers.IO) {
            pin = getPinCodeUseCase()
        }
    }

    fun checkPassword(password: String): Boolean {
        return password.hashCode() == pin
    }
}