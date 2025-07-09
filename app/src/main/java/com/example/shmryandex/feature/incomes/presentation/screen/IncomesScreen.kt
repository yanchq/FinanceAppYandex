package com.example.shmryandex.feature.incomes.presentation.screen

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
import com.example.shmryandex.R
import com.example.shmryandex.feature.incomes.domain.entity.Income
import com.example.shmryandex.core.utils.toCurrencyString
import com.example.shmryandex.feature.incomes.presentation.components.IncomesScreenContent
import com.example.shmryandex.feature.incomes.presentation.viewmodel.IncomesViewModel
import com.example.shmryandex.core.presentation.ui.theme.DividerGrey
import com.example.shmryandex.core.presentation.ui.theme.SecondaryGreen
import com.example.shmryandex.core.utils.rememberMyViewModel

/**
 * Composable-функция, представляющая экран доходов.
 * Отображает список доходов с возможностью просмотра деталей каждой транзакции
 * и общей суммы за период.
 */
@Composable
fun IncomesScreen(viewModel: IncomesViewModel = rememberMyViewModel()) {

    val uiState = viewModel.uiState.collectAsState()

   IncomesScreenContent(
       uiState.value
   ) { event ->
       viewModel.onEvent(event)
   }
}
