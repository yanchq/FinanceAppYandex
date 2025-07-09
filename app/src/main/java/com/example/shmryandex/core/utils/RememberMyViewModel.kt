package com.example.shmryandex.core.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.shmryandex.FinanceApp

@Composable
inline fun <reified T : ViewModel> rememberMyViewModel(): T {
    val context = LocalContext.current.applicationContext as FinanceApp
    val factory = (context.appComponent.viewModelFactory())
    return viewModel(factory = factory)
}