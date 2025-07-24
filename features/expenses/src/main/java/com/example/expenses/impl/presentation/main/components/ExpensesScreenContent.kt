package com.example.expenses.impl.presentation.main.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.expenses.R
import com.example.core.utils.toCurrencyString
import com.example.core.utils.ui.localizedString
import com.example.expenses.impl.domain.entity.Expense
import com.example.expenses.impl.presentation.main.contract.ExpensesUIEvent
import com.example.expenses.impl.presentation.main.contract.ExpensesUIState
import com.example.core.utils.ui.theme.DividerGrey
import com.example.core.utils.ui.theme.SecondaryGreen
import com.example.core.utils.ui.theme.TextBlack

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
    onItemClicked: (Int) -> Unit,
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
                .background(MaterialTheme.colorScheme.secondaryContainer)
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
                text = localizedString(com.example.core.R.string.total_amount),
                color = TextBlack
            )
            Text(
                text = uiState.summary.toCurrencyString("₽"),
                color = TextBlack
            )
        }

        LazyColumn {
            items(uiState.expenses) { expense ->
                ExpenseCard(
                    expense = expense,
                    onItemClicked = {
                        onItemClicked(expense.id.toInt())
                    }
                )
            }
        }
    }
}

@Composable
private fun ExpenseCard(
    expense: Expense,
    onItemClicked: () -> Unit
) {
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
            .clickable {
                onItemClicked()
            }

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
                    style = MaterialTheme.typography.bodyMedium,
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
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