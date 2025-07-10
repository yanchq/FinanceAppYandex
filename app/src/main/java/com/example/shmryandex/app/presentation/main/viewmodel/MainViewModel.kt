package com.example.shmryandex.app.presentation.main.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.shmryandex.app.presentation.main.contract.MainUIEffect
import com.example.shmryandex.app.presentation.main.contract.MainUIEvent
import com.example.shmryandex.app.presentation.main.contract.MainUIState
import com.example.core.domain.usecase.GetSelectedAccountUseCase
import com.example.core.presentation.mvi.BaseViewModel
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.URLEncoder
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getSelectedAccountUseCase: GetSelectedAccountUseCase
): BaseViewModel<MainUIEvent, MainUIState, MainUIEffect>
    (MainUIState()){

    override fun onEvent(event: MainUIEvent) {
        when (event) {
            MainUIEvent.EditAccountIconClicked -> {
                navigateToEditAccountScreen()
            }
        }
    }

    private fun navigateToEditAccountScreen() {
        viewModelScope.launch(Dispatchers.IO) {
            val selectedAccount = getSelectedAccountUseCase()
            if (selectedAccount != null) {
                val gson = Gson()
                val accountJson = gson.toJson(selectedAccount)
                val encoded = URLEncoder.encode(accountJson, "UTF-8")
                setEffect(MainUIEffect.NavigateToEditAccountScreen(encoded))
            }
        }
    }
}