package com.example.shmryandex.app.presentation.main.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.shmryandex.app.presentation.main.contract.MainUIEffect
import com.example.shmryandex.app.presentation.main.contract.MainUIEvent
import com.example.shmryandex.app.presentation.main.contract.MainUIState
import com.example.core.domain.usecase.GetSelectedAccountUseCase
import com.example.core.presentation.mvi.BaseViewModel
import com.example.shmryandex.app.domain.usecase.sync.GetLastSyncInfoUseCase
import com.example.shmryandex.app.domain.usecase.haptic.TriggerHapticUseCase
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.URLEncoder
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getSelectedAccountUseCase: GetSelectedAccountUseCase,
    private val getLastSyncInfoUseCase: GetLastSyncInfoUseCase,
    private val triggerHapticUseCase: TriggerHapticUseCase,
): BaseViewModel<MainUIEvent, MainUIState, MainUIEffect>
    (MainUIState()){

        init {
            getLastSyncInfo()
        }

    override fun onEvent(event: MainUIEvent) {
        when (event) {
            MainUIEvent.EditAccountIconClicked -> {
                navigateToEditAccountScreen()
            }

            MainUIEvent.HapticButtonClicked -> {
                viewModelScope.launch(Dispatchers.IO) {
                    triggerHapticUseCase()
                }
            }
        }
    }

    private fun navigateToEditAccountScreen() {
        viewModelScope.launch(Dispatchers.IO) {
            triggerHapticUseCase()
            val selectedAccount = getSelectedAccountUseCase()
            if (selectedAccount != null) {
                val gson = Gson()
                val accountJson = gson.toJson(selectedAccount)
                val encoded = URLEncoder.encode(accountJson, "UTF-8")
                setEffect(MainUIEffect.NavigateToEditAccountScreen(encoded))
            }
        }
    }

    private fun getLastSyncInfo() {
        viewModelScope.launch(Dispatchers.IO) {
            val syncInfo = getLastSyncInfoUseCase()
            val time = syncInfo.first
            val status = syncInfo.second
            if (time != null && status != null) {
                setState(currentState.copy(
                    syncTime = time,
                    syncStatus = status
                ))
                setEffect(MainUIEffect.ShowSyncInfoDialog)
            }
        }
    }
}