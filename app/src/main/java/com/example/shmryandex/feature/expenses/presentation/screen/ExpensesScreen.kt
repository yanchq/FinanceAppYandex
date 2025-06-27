package com.example.shmryandex.feature.expenses.presentation.screen

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
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.shmryandex.R
import com.example.shmryandex.feature.expenses.domain.entity.Expense
import com.example.shmryandex.core.utils.toCurrencyString
import com.example.shmryandex.feature.expenses.presentation.components.ExpensesScreenContent
import com.example.shmryandex.feature.expenses.presentation.viewmodel.ExpensesViewModel
import com.example.shmryandex.core.presentation.ui.theme.DividerGrey
import com.example.shmryandex.core.presentation.ui.theme.SecondaryGreen


@Composable
fun ExpensesScreen(viewModel: ExpensesViewModel = hiltViewModel()) {

    val uiState = viewModel.uiState.collectAsState()

    ExpensesScreenContent(
        uiState.value,
    ) { event ->
        viewModel.onEvent(event)
    }
}

