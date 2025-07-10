package com.example.accounts.impl.presentation.addaccount.contract

import com.example.core.presentation.mvi.UIState

/**
 * Состояние UI для экрана добавления нового счета.
 * Базовая реализация состояния без дополнительных полей.
 */
data class AddAccountUIState(
    val state: Nothing? = null
): UIState
