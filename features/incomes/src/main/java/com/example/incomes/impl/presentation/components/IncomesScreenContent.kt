package com.example.incomes.impl.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.example.incomes.R
import com.example.core.utils.toCurrencyString
import com.example.incomes.impl.domain.entity.Income
import com.example.incomes.impl.presentation.contract.IncomesUIEvent
import com.example.incomes.impl.presentation.contract.IncomesUIState
import com.example.core.utils.ui.theme.DividerGrey
import com.example.core.utils.ui.theme.SecondaryGreen

@Composable
fun IncomesScreenContent(
    onItemClicked: (Int) -> Unit,
    uiState: IncomesUIState,
    sendEvent: (IncomesUIEvent) -> Unit
) {

    LaunchedEffect(Unit) {
        sendEvent(IncomesUIEvent.LoadIncomes)
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
            items(uiState.incomes) { income ->
                IncomeCard(
                    onItemClicked = {
                        onItemClicked(income.id.toInt())
                    },
                    income = income
                )
            }
        }
    }
}

@Composable
private fun IncomeCard(
    onItemClicked: () -> Unit,
    income: Income
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

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = income.name,
                style = MaterialTheme.typography.bodyMedium
            )
            if (income.comment != "") {
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = income.comment,
                    style = MaterialTheme.typography.bodySmall
                )
            }

        }
        Text(
            text = income.amount.toCurrencyString(income.currency),
            style = MaterialTheme.typography.bodyMedium
        )
        Image(
            painter = painterResource(R.drawable.ic_more),
            contentDescription = null
        )
    }
}