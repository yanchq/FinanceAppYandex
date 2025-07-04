package com.example.shmryandex.app.presentation.screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.shmryandex.R
import com.example.shmryandex.app.navigation.AppNavGraph
import com.example.shmryandex.app.navigation.Screen
import com.example.shmryandex.app.presentation.components.MainScreenContent
import com.example.shmryandex.app.presentation.contract.MainUIEffect
import com.example.shmryandex.app.presentation.viewmodel.MainViewModel
import com.example.shmryandex.feature.accounts.presentation.main.screen.AccountScreen
import com.example.shmryandex.feature.accounts.presentation.addaccount.screen.AddAccountScreen
import com.example.shmryandex.feature.categories.presentation.screen.CategoriesScreen
import com.example.shmryandex.feature.expenses.presentation.screen.ExpensesScreen
import com.example.shmryandex.feature.incomes.presentation.screen.IncomesScreen
import com.example.shmryandex.feature.options.OptionsScreen
import com.example.shmryandex.core.presentation.ui.theme.PrimaryGreen
import com.example.shmryandex.feature.accounts.presentation.editaccount.screen.EditAccountScreen
import com.example.shmryandex.feature.history.presentation.screen.HistoryScreen
import com.google.gson.Gson
import kotlinx.coroutines.flow.collect
import java.net.URLEncoder

/**
 * Основной экран приложения.
 * Содержит нижнюю навигационную панель, верхний бар и контейнер для контента.
 * Управляет навигацией между основными разделами приложения.
 */
@Composable
fun MainScreen(viewModel: MainViewModel = hiltViewModel()) {

    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    val navHostController = rememberNavController()

    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is MainUIEffect.NavigateToEditAccountScreen -> {
                    navHostController.navigate(Screen.EditAccount.route)
                }
            }
        }
    }

    MainScreenContent(
        uiState = uiState.value,
        navHostController = navHostController
    ) { event ->
        viewModel.onEvent(event)
    }
}

