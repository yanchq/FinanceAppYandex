package com.example.accounts.impl.presentation.main.contract

import com.example.core.presentation.mvi.UIEffect

/**
 * Sealed класс, определяющий одноразовые UI-эффекты для экрана счетов.
 * Используется для обработки побочных эффектов в MVI архитектуре,
 * таких как навигация или показ сообщений.
 */
sealed class AccountsUIEffect: UIEffect {
}