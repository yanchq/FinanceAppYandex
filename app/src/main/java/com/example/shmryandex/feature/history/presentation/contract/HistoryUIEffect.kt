package com.example.shmryandex.feature.history.presentation.contract

import com.example.shmryandex.core.presentation.mvi.UIEffect

/**
 * Sealed класс, представляющий одноразовые UI-эффекты для экрана истории транзакций.
 * Наследуется от [UIEffect] и определяет возможные побочные эффекты UI,
 * такие как показ сообщений об ошибках или навигация.
 */
sealed class HistoryUIEffect: UIEffect {
}