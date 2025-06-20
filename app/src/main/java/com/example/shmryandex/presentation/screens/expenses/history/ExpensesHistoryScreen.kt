package com.example.shmryandex.presentation.screens.expenses.history

import android.app.DatePickerDialog
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.shmryandex.R
import com.example.shmryandex.presentation.toCurrencyString
import com.example.shmryandex.ui.theme.DividerGrey
import com.example.shmryandex.ui.theme.SecondaryGreen
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun ExpensesHistoryScreen(
    viewModel: ExpensesHistoryViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

} 