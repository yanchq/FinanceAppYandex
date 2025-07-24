package com.example.shmryandex.app.presentation.options.pincode

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shmryandex.app.domain.usecase.pincode.SetPinCodeUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class PinCodeViewModel @Inject constructor(
    private val setPinCodeUseCase: SetPinCodeUseCase
): ViewModel() {

    private val _effect: Channel<Boolean> = Channel()
    val effect = _effect.receiveAsFlow()

    fun setPinCode(pin: String) {
        viewModelScope.launch(Dispatchers.IO) {
            setPinCodeUseCase(pin.hashCode())
            _effect.send(true)
        }
    }
}