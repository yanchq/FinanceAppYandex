package com.example.expenses.impl.presentation.addexpense.contract

import com.example.core.domain.entity.Account
import com.example.core.domain.entity.Category
import com.example.core.presentation.mvi.UIEvent

sealed interface AddExpenseUIEvent: UIEvent {

    data object AddExpenseEvent: AddExpenseUIEvent

    data class CategoryChanged(val category: Category): AddExpenseUIEvent

    data class AccountsSelected(val account: Account): AddExpenseUIEvent

    data class AmountChanged(val amount: String): AddExpenseUIEvent

    data class CommentChanged(val comment: String): AddExpenseUIEvent

    data class DateChanged(val date: String): AddExpenseUIEvent

    data class TimeChanged(val time: String): AddExpenseUIEvent
}