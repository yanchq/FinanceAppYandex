package com.example.shmryandex.presentation.screens.expenses

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.shmryandex.R
import com.example.shmryandex.domain.entity.Category
import com.example.shmryandex.domain.entity.Expense
import com.example.shmryandex.presentation.navigation.AppNavGraph
import com.example.shmryandex.presentation.navigation.Screen
import com.example.shmryandex.presentation.screens.NavigationItem
import com.example.shmryandex.presentation.screens.account.AccountScreen
import com.example.shmryandex.presentation.screens.categories.CategoriesScreen
import com.example.shmryandex.presentation.screens.incomes.IncomesScreen
import com.example.shmryandex.presentation.screens.options.OptionsScreen
import com.example.shmryandex.presentation.toCurrencyString
import com.example.shmryandex.ui.theme.DividerGrey
import com.example.shmryandex.ui.theme.Grey
import com.example.shmryandex.ui.theme.PrimaryGreen
import com.example.shmryandex.ui.theme.SHMRYandexTheme
import com.example.shmryandex.ui.theme.SecondaryGreen
import com.example.shmryandex.ui.theme.TextGrey


@Composable
fun ExpensesScreen(viewModel: ExpensesViewModel = hiltViewModel()) {

    val uiState = viewModel.uiState.collectAsState()

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
                text = uiState.value.summary.toCurrencyString(),
                style = MaterialTheme.typography.bodyMedium
            )
        }

        LazyColumn {
            items(uiState.value.expenses) { expense ->
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
                text = expense.category.emoji
            )
        }

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = expense.category.name,
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
            text = expense.amount.toCurrencyString(),
            style = MaterialTheme.typography.bodyMedium
        )
        Image(
            painter = painterResource(R.drawable.ic_more),
            contentDescription = null
        )
    }
}