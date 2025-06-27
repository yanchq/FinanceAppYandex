package com.example.shmryandex.feature.categories.presentation.screen

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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.shmryandex.R
import com.example.shmryandex.core.presentation.ui.theme.DividerGrey
import com.example.shmryandex.core.presentation.ui.theme.SecondaryGreen
import com.example.shmryandex.feature.categories.presentation.components.CategoriesScreenContent
import com.example.shmryandex.feature.categories.presentation.viewmodel.CategoriesViewModel

/**
 * Composable функция экрана категорий.
 * Отображает список категорий транзакций с возможностью их выбора.
 * Использует CategoriesViewModel для управления данными и состоянием.
 */
@Composable
fun CategoriesScreen(viewModel: CategoriesViewModel = hiltViewModel()) {

    val uiState = viewModel.uiState.collectAsState()

    CategoriesScreenContent(
        uiState.value
    ) { event ->
        viewModel.onEvent(event)
    }
}
