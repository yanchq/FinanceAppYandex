package com.example.shmryandex

import androidx.activity.ComponentActivity
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.core.utils.ui.MainColorType
import com.example.core.utils.ui.theme.SHMRYandexTheme
import com.example.shmryandex.app.presentation.options.main.components.ThemeToggle
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Рабочие UI-тесты для проверки функциональности переключения темы
 * Используют ComponentActivity для корректной инициализации Compose
 */
@RunWith(AndroidJUnit4::class)
class ThemeToggleWorkingUITest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun themeToggle_initialState_isCorrect() {
        // Given: Инициализация с светлой темой
        composeTestRule.setContent {
            SHMRYandexTheme(
                darkTheme = false,
                mainColor = MainColorType.getDefault()
            ) {
                ThemeToggle(
                    isDarkTheme = false,
                    onToggle = { }
                )
            }
        }

        // Then: Проверяем отображение и состояние
        composeTestRule
            .onNodeWithText("Тёмная тема")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithTag("theme_toggle_switch")
            .assertExists()
            .assertIsOff()
    }

    @Test
    fun themeToggle_click_changesState() {
        var isDarkTheme by mutableStateOf(false)
        var wasClicked = false

        // Given: Компонент с callback
        composeTestRule.setContent {
            SHMRYandexTheme(
                darkTheme = isDarkTheme,
                mainColor = MainColorType.getDefault()
            ) {
                ThemeToggle(
                    isDarkTheme = isDarkTheme,
                    onToggle = { newValue ->
                        isDarkTheme = newValue
                        wasClicked = true
                    }
                )
            }
        }

        // When: Кликаем на переключатель
        composeTestRule
            .onNodeWithTag("theme_toggle_switch")
            .performClick()

        // Then: Состояние изменилось
        assert(isDarkTheme) { "Тема должна быть тёмной" }
        assert(wasClicked) { "Callback должен быть вызван" }

        composeTestRule
            .onNodeWithTag("theme_toggle_switch")
            .assertIsOn()
    }

    @Test
    fun themeToggle_doubleClick_togglesTwice() {
        var isDarkTheme by mutableStateOf(false)
        var clickCount = 0

        composeTestRule.setContent {
            SHMRYandexTheme(
                darkTheme = isDarkTheme,
                mainColor = MainColorType.getDefault()
            ) {
                ThemeToggle(
                    isDarkTheme = isDarkTheme,
                    onToggle = { newValue ->
                        isDarkTheme = newValue
                        clickCount++
                    }
                )
            }
        }

        // When: Два клика
        composeTestRule
            .onNodeWithTag("theme_toggle_switch")
            .performClick()

        composeTestRule
            .onNodeWithTag("theme_toggle_switch")
            .performClick()

        // Then: Вернулись к исходному состоянию
        assert(!isDarkTheme) { "Должна быть светлая тема" }
        assert(clickCount == 2) { "Должно быть 2 клика" }

        composeTestRule
            .onNodeWithTag("theme_toggle_switch")
            .assertIsOff()
    }

    @Test
    fun themeToggle_darkThemeEnabled_showsCorrectState() {
        composeTestRule.setContent {
            SHMRYandexTheme(
                darkTheme = true,
                mainColor = MainColorType.getDefault()
            ) {
                ThemeToggle(
                    isDarkTheme = true,
                    onToggle = { }
                )
            }
        }

        // Then: Switch включен для тёмной темы
        composeTestRule
            .onNodeWithTag("theme_toggle_switch")
            .assertIsOn()

        composeTestRule
            .onNodeWithText("Тёмная тема")
            .assertIsDisplayed()
    }

    @Test
    fun themeToggle_isToggleable() {
        composeTestRule.setContent {
            SHMRYandexTheme(
                darkTheme = false,
                mainColor = MainColorType.getDefault()
            ) {
                ThemeToggle(
                    isDarkTheme = false,
                    onToggle = { }
                )
            }
        }

        // Then: Switch должен быть переключаемым
        composeTestRule
            .onNodeWithTag("theme_toggle_switch")
            .assertIsToggleable()
    }
} 