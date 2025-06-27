package com.example.shmryandex.app.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.compose.rememberNavController
import com.example.shmryandex.app.navigation.RootNavGraph
import com.example.shmryandex.core.presentation.ui.theme.SHMRYandexTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val networkViewModel: NetworkViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()

        super.onCreate(savedInstanceState)

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
                RootNavGraph(navController = rememberNavController())
            }
        }
    }
}
