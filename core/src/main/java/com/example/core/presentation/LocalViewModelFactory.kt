package com.example.core.presentation

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.lifecycle.ViewModelProvider
import java.util.Locale

val LocalViewModelFactory = staticCompositionLocalOf<ViewModelProvider.Factory> { error("null") }

val LocalAppLocale = compositionLocalOf { Locale.getDefault() }