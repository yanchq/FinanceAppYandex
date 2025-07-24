package com.example.shmryandex.app.di.module

import androidx.lifecycle.ViewModel
import com.example.core.di.ViewModelKey
import com.example.shmryandex.app.presentation.main.viewmodel.MainViewModel
import com.example.shmryandex.app.presentation.main.viewmodel.NetworkViewModel
import com.example.shmryandex.app.presentation.options.changelocale.ChangeLocaleViewModel
import com.example.shmryandex.app.presentation.options.haptic.HapticViewModel
import com.example.shmryandex.app.presentation.options.main.viewmodel.OptionsViewModel
import com.example.shmryandex.app.presentation.options.maincolor.MainColorViewModel
import com.example.shmryandex.app.presentation.options.sync.SyncViewModel
import com.example.shmryandex.app.presentation.splash.viewmodel.SplashViewModel
import com.example.shmryandex.app.presentation.theme.ThemeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun bindMainViewModel(mainViewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(NetworkViewModel::class)
    fun bindNetworkViewModel(networkViewModel: NetworkViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel::class)
    fun bindSplashViewModel(splashViewModel: SplashViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(OptionsViewModel::class)
    fun bindOptionsViewModel(optionsViewModel: OptionsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ThemeViewModel::class)
    fun bindThemeViewModel(themeViewModel: ThemeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ChangeLocaleViewModel::class)
    fun bindChangeLocaleViewModel(changeLocaleViewModel: ChangeLocaleViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MainColorViewModel::class)
    fun bindChangeMainColorViewModel(mainColorViewModel: MainColorViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SyncViewModel::class)
    fun bindSyncViewModel(syncViewModel: SyncViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HapticViewModel::class)
    fun bindHapticViewModel(hapticViewModel: HapticViewModel): ViewModel
}