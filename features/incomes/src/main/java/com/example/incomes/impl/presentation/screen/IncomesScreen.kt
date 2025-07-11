package com.example.incomes.impl.presentation.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.core.presentation.LocalViewModelFactory
import com.example.incomes.impl.presentation.components.IncomesScreenContent
import com.example.incomes.impl.presentation.viewmodel.IncomesViewModel


/**
 * Composable-функция, представляющая экран доходов.
 * Отображает список доходов с возможностью просмотра деталей каждой транзакции
 * и общей суммы за период.
 */
@Composable
fun IncomesScreen(
    onItemClicked: (Int) -> Unit,
    viewModel: IncomesViewModel = viewModel(factory = LocalViewModelFactory.current)
) {

    val uiState = viewModel.uiState.collectAsState()

   IncomesScreenContent(
       onItemClicked = {
           onItemClicked(it)
       },
       uiState =  uiState.value
   ) { event ->
       viewModel.onEvent(event)
   }
}
