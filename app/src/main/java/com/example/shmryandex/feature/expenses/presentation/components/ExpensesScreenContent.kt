package com.example.shmryandex.feature.expenses.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.shmryandex.R
import com.example.shmryandex.core.utils.toCurrencyString
import com.example.shmryandex.feature.expenses.domain.entity.Expense
import com.example.shmryandex.feature.expenses.presentation.contract.ExpensesUIEvent
import com.example.shmryandex.feature.expenses.presentation.contract.ExpensesUIState
import com.example.shmryandex.core.presentation.ui.theme.DividerGrey
import com.example.shmryandex.core.presentation.ui.theme.SecondaryGreen

/**
 * Composable функция контента экрана расходов.
 * Отображает общую сумму расходов и список расходных операций.
 * Каждая операция представлена в виде карточки с эмодзи категории,
 * названием, комментарием и суммой.
 * @param uiState текущее состояние UI с списком расходов
 * @param sendEvent функция для отправки событий в ViewModel
 */
@Composable
fun ExpensesScreenContent(
    uiState: ExpensesUIState,
    sendEvent: (ExpensesUIEvent) -> Unit
) {

    LaunchedEffect(Unit) {
        sendEvent(ExpensesUIEvent.LoadExpenses)
    }
    Column {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .background(SecondaryGreen)
                .fillMaxWidth()
                .height(56.dp)
                .drawBehind {
                    val strokeWidth = 0.7.dp.toPx()
                    drawLine(
                        color = DividerGrey,
                        start = Offset(0f, size.height - strokeWidth / 2),
                        end = Offset(size.width, size.height - strokeWidth / 2),
                        strokeWidth = strokeWidth
                    )
                }
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Text(
                text = "Всего",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = uiState.summary.toCurrencyString("₽"),
                style = MaterialTheme.typography.bodyMedium
            )
        }

        LazyColumn {
            items(uiState.expenses) { expense ->
                ExpenseCard(
                    expense
                )
            }
        }
    }
}

@Composable
private fun ExpenseCard(expense: Expense) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .drawBehind {
                val strokeWidth = 0.7.dp.toPx()
                drawLine(
                    color = DividerGrey,
                    start = Offset(0f, size.height - strokeWidth / 2),
                    end = Offset(size.width, size.height - strokeWidth / 2),
                    strokeWidth = strokeWidth
                )
            }
            .padding(horizontal = 16.dp, vertical = 8.dp)

    ) {
        Box(
            modifier = Modifier
                .size(24.dp)
                .background(SecondaryGreen, CircleShape)
        ) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = expense.emoji
            )
        }

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = expense.name,
                style = MaterialTheme.typography.bodyMedium
            )
            if (expense.comment != "") {
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = expense.comment,
                    style = MaterialTheme.typography.bodySmall
                )
            }

        }

        Text(
            text = expense.amount.toCurrencyString(expense.currency),
            style = MaterialTheme.typography.bodyMedium
        )
        Image(
            painter = painterResource(R.drawable.ic_more),
            contentDescription = null
        )
    }
}