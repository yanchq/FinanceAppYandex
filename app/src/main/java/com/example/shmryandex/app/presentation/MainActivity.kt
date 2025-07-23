package com.example.shmryandex.app.presentation

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.compose.rememberNavController
import com.example.core.presentation.LocalViewModelFactory
import com.example.core.utils.ui.theme.SHMRYandexTheme
import com.example.shmryandex.app.navigation.RootNavGraph
import com.example.shmryandex.app.presentation.main.viewmodel.NetworkViewModel
import com.example.shmryandex.app.presentation.theme.ThemeViewModel
import com.example.shmryandex.appComponent

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
    private lateinit var themeViewModel: ThemeViewModel



    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()

        super.onCreate(savedInstanceState)

        appComponent.inject(this)
        networkViewModel = ViewModelProvider(this, viewModelFactory)[NetworkViewModel::class]
        themeViewModel = ViewModelProvider(this, viewModelFactory)[ThemeViewModel::class]

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
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

                launch {
                    themeViewModel.locale.collect { locale ->
                        handleLocaleChangeSafe(locale)
                    }
                }
            }
        }

        enableEdgeToEdge()
        setContent {
            val isDarkTheme by themeViewModel.isDarkTheme.collectAsStateWithLifecycle()
            val mainColor by themeViewModel.mainColor.collectAsStateWithLifecycle()

            SHMRYandexTheme(
                darkTheme = isDarkTheme,
                mainColor = mainColor) {
                CompositionLocalProvider(
                    LocalViewModelFactory provides appComponent.viewModelFactory(),
                ) {
                    RootNavGraph(navController = rememberNavController())
                }
            }
        }
    }

    private var currentLocale: String? = null
    private var isFirstLocaleLoad = true
    
    private fun handleLocaleChangeSafe(newLocale: String) {
        Log.d("LocaleChange", "Locale received: $newLocale, current: $currentLocale, isFirst: $isFirstLocaleLoad")
        
        // При первой загрузке просто сохраняем локаль без обработки
        if (isFirstLocaleLoad) {
            currentLocale = newLocale
            isFirstLocaleLoad = false
            applyLocaleToApplication(newLocale)
            Log.d("LocaleChange", "Initial locale set to: $newLocale")
            return
        }
        
        // Проверяем, что локаль действительно изменилась
        if (currentLocale == newLocale) {
            Log.d("LocaleChange", "Same locale, skipping")
            return
        }
        
        Log.d("LocaleChange", "Locale changed from $currentLocale to $newLocale")
        
        // Сохраняем новую локаль
        val previousLocale = currentLocale
        currentLocale = newLocale
        
        // Применяем локаль к ресурсам приложения
        applyLocaleToApplication(newLocale)
        
        // Показываем уведомление об изменении языка
        showLocaleChangedNotification(newLocale, previousLocale)
        
        Log.d("LocaleChange", "Locale applied successfully")
    }


    private fun applyLocaleToApplication(locale: String) {
        Log.d("LocaleChange", "Applying locale: $locale")
        // Обновляем контекст приложения с новой локалью
        val updatedContext = themeViewModel.updateLocaleContext(applicationContext)

        // Дополнительно можно сохранить локаль в системных настройках
        val configuration = updatedContext.resources.configuration
        resources.updateConfiguration(configuration, resources.displayMetrics)
    }
    
    private fun showLocaleChangedNotification(newLocale: String, previousLocale: String?) {
        val languageName = when(newLocale) {
            "ru" -> "Русский"
            "en" -> "English"
            else -> newLocale.uppercase()
        }
        
        // Показываем уведомление только если предыдущая локаль была установлена
        if (previousLocale != null) {
            Toast.makeText(
                this,
                "Язык изменен на $languageName",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}
