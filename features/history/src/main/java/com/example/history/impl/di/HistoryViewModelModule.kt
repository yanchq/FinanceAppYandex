package com.example.history.impl.di

import androidx.lifecycle.ViewModel
import com.example.core.di.ViewModelKey
import com.example.history.impl.presentation.analytics.viewmodel.AnalyticsViewModel
import com.example.history.impl.presentation.main.viewmodel.HistoryViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface HistoryViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(HistoryViewModel::class)
    fun bindHistoryViewModel(historyViewModel: HistoryViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AnalyticsViewModel::class)
    fun bindAnalyticsViewModel(analyticsViewModel: AnalyticsViewModel): ViewModel
}