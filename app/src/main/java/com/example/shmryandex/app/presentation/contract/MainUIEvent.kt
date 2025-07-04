package com.example.shmryandex.app.presentation.contract

import com.example.shmryandex.core.presentation.mvi.UIEvent

sealed class MainUIEvent : UIEvent {
    data object EditAccountIconClicked: MainUIEvent()
}