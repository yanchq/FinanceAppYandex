package com.example.history.impl.presentation.main.contract

import com.example.core.presentation.mvi.UIState
import com.example.history.impl.domain.entity.HistoryItem
import java.time.LocalDate

/**
 * Data класс, представляющий состояние UI экрана истории транзакций.
 * Содержит информацию о выбранном периоде, списке транзакций и общей сумме.
 *
 * @property startDate Начальная дата периода (по умолчанию первый день текущего месяца)
 * @property endDate Конечная дата периода (по умолчанию текущая дата)
 * @property items Список элементов истории транзакций
 * @property totalAmount Общая сумма всех транзакций в списке (вычисляемое поле)
 */
data class HistoryUIState(
    val startDate: String = LocalDate.now().withDayOfMonth(1).toString(),
    val endDate: String = LocalDate.now().toString(),
    val items: List<HistoryItem> = emptyList(),
): UIState {
    val totalAmount = items.sumOf { it.amount }
}