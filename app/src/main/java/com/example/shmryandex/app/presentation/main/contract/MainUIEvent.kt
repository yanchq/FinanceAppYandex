package com.example.shmryandex.app.presentation.main.contract

import com.example.core.presentation.mvi.UIEvent

sealed class MainUIEvent : UIEvent {
    data object EditAccountIconClicked: MainUIEvent()
}