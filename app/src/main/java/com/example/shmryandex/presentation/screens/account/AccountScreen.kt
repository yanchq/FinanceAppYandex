package com.example.shmryandex.presentation.screens.account

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.shmryandex.R
import com.example.shmryandex.presentation.toCurrencyString
import com.example.shmryandex.ui.theme.DividerGrey
import com.example.shmryandex.ui.theme.SecondaryGreen

@Composable
fun AccountScreen(viewModel: AccountViewModel = hiltViewModel()) {

    val uiState = viewModel.uiState.collectAsState()

    Column {
        BalanceCard(uiState.value!!.balance)
        CurrencyCard(uiState.value!!.currency)
    }
}

@Composable
private fun BalanceCard(balance: Int) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
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

        Image(
            painter = painterResource(R.drawable.ic_balance),
            contentDescription = null
        )
        Text(
            text = "Баланс",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = balance.toCurrencyString(),
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.End
        )

        Icon(
            painter = painterResource(R.drawable.ic_more),
            contentDescription = null
        )
    }
}

@Composable
private fun CurrencyCard(currency: String) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .background(SecondaryGreen)
            .fillMaxWidth()
            .height(56.dp)
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {

        Text(
            text = "Валюта",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = currency,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.End
        )

        Icon(
            painter = painterResource(R.drawable.ic_more),
            contentDescription = null
        )
    }
}