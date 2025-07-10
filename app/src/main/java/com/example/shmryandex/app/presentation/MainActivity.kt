package com.example.shmryandex.app.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.CompositionLocalProvider
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.compose.rememberNavController
import com.example.core.presentation.LocalViewModelFactory
import com.example.shmryandex.app.navigation.RootNavGraph
import com.example.shmryandex.app.presentation.main.viewmodel.NetworkViewModel
import com.example.shmryandex.appComponent
import com.example.core.utils.ui.theme.SHMRYandexTheme
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Главная активность приложения.
 * Инициализирует навигацию, тему и обработку сетевых событий.
 */
class MainActivity : ComponentActivity() {


    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var networkViewModel: NetworkViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()

        super.onCreate(savedInstanceState)

        appComponent.inject(this)
        networkViewModel = ViewModelProvider(this, viewModelFactory)[NetworkViewModel::class]

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                networkViewModel.events.collect { event ->
                    when (event) {
                        is NetworkEvent.ShowNoConnectionToast -> {
                            Toast.makeText(
                                this@MainActivity,
                                "Отсутствует подключение к интернету",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }
        }

        enableEdgeToEdge()
        setContent {
            SHMRYandexTheme {
                CompositionLocalProvider(
                    LocalViewModelFactory provides appComponent.viewModelFactory()
                ) {
                    RootNavGraph(navController = rememberNavController())
                }
            }
        }
    }
}
