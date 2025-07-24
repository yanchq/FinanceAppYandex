package com.example.shmryandex.app.presentation.options.haptic

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.data.haptic.HapticType
import com.example.shmryandex.app.domain.usecase.haptic.GetHapticEnabledStateUseCase
import com.example.shmryandex.app.domain.usecase.haptic.GetHapticTypeUseCase
import com.example.shmryandex.app.domain.usecase.haptic.SetHapticEnabledStateUseCase
import com.example.shmryandex.app.domain.usecase.haptic.SetHapticTypeUseCase
import com.example.shmryandex.app.domain.usecase.haptic.TriggerHapticUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class HapticViewModel @Inject constructor(
    private val getHapticTypeUseCase: GetHapticTypeUseCase,
    private val setHapticTypeUseCase: SetHapticTypeUseCase,
    private val triggerHapticUseCase: TriggerHapticUseCase,
    private val getHapticEnabledStateUseCase: GetHapticEnabledStateUseCase,
    private val setHapticEnabledStateUseCase: SetHapticEnabledStateUseCase
): ViewModel() {

    private val _hapticType = MutableStateFlow(HapticType.getDefault())
    val hapticType = _hapticType.asStateFlow()

    private val _hapticEnabledState = MutableStateFlow(false)
    val hapticEnabledState = _hapticEnabledState.asStateFlow()

    init {
        getHapticEnabledState()
        getHapticType()
    }

    fun setHapticType(type: HapticType) {
        viewModelScope.launch(Dispatchers.IO) {
            setHapticTypeUseCase(type)
            _hapticType.value = type
            triggerHapticUseCase()
        }
    }

    fun setHapticEnabledState(state: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            setHapticEnabledStateUseCase(state)
            _hapticEnabledState.value = state
        }
    }

    private fun getHapticType() {
        viewModelScope.launch(Dispatchers.IO) {
            _hapticType.value = getHapticTypeUseCase()
        }
    }

    private fun getHapticEnabledState() {
        viewModelScope.launch(Dispatchers.IO) {
            _hapticEnabledState.value = getHapticEnabledStateUseCase()
        }
    }
}