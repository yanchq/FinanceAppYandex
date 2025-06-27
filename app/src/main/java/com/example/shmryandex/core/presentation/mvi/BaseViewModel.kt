package com.example.shmryandex.core.presentation.mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<Event : UIEvent, State : UIState, Effect : UIEffect>
    (initialState: State): ViewModel() {

    val currentState: State
        get() = uiState.value

    private val _uiState: MutableStateFlow<State> = MutableStateFlow(initialState)
    val uiState = _uiState.asStateFlow()

    private val _effect: Channel<Effect> = Channel()
    val effect = _effect.receiveAsFlow()

    protected fun setState(newState: State) {
        _uiState.value = newState
    }

    protected fun setEffect(effect: Effect) {
        viewModelScope.launch {
            _effect.send(effect)
        }
    }

    abstract fun onEvent(event: Event)
}