package com.example.expenses.impl.presentation.editexpense.contract

import com.example.core.domain.entity.Account
import com.example.core.domain.entity.Category
import com.example.core.domain.entity.DetailedTransaction
import com.example.core.presentation.mvi.UIEvent

sealed interface EditExpenseUIEvent: UIEvent {

    data object AddExpenseEvent: EditExpenseUIEvent

    data class TransactionInit(val transactionId: Int): EditExpenseUIEvent

    data class CategoryChanged(val category: Category): EditExpenseUIEvent

    data class AccountsSelected(val account: Account): EditExpenseUIEvent

    data class AmountChanged(val amount: String): EditExpenseUIEvent

    data class CommentChanged(val comment: String): EditExpenseUIEvent

    data class DateChanged(val date: String): EditExpenseUIEvent

    data class TimeChanged(val time: String): EditExpenseUIEvent
}