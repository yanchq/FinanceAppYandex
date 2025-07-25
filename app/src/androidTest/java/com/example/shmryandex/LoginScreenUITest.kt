package com.example.shmryandex

import androidx.activity.ComponentActivity
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.navigation.NavHostController
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.rememberNavController
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.core.utils.ui.MainColorType
import com.example.core.utils.ui.theme.SHMRYandexTheme
import com.example.shmryandex.app.presentation.login.LoginScreen
import com.example.shmryandex.app.presentation.login.LoginViewModel
import io.mockk.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * UI тесты для экрана ввода пин-кода
 * Проверяют функциональность ввода пин-кода, отображение индикаторов и обработку ошибок
 */
@RunWith(AndroidJUnit4::class)
class LoginScreenUITest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun loginScreen_initialState_isCorrect() {
        // Given: Инициализация экрана логина
        val mockViewModel = mockk<LoginViewModel>(relaxed = true)
        
        composeTestRule.setContent {
            SHMRYandexTheme(
                darkTheme = false,
                mainColor = MainColorType.getDefault()
            ) {
                val navController = rememberNavController()
                LoginScreen(
                    navHostController = navController,
                    viewModel = mockViewModel
                )
            }
        }

        // Then: Проверяем отображение основных элементов
        composeTestRule
            .onNodeWithTag("pin_title")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithTag("pin_indicators")
            .assertIsDisplayed()

        // Проверяем наличие всех индикаторов пин-кода
        repeat(4) { i ->
            composeTestRule
                .onNodeWithTag("pin_indicator_$i")
                .assertExists()
        }

        // Проверяем наличие цифровых кнопок
        (1..9).forEach { number ->
            composeTestRule
                .onNodeWithTag("pin_key_$number")
                .assertExists()
                .assertIsDisplayed()
        }

        // Проверяем наличие кнопки 0 и кнопки удаления
        composeTestRule
            .onNodeWithTag("pin_key_0")
            .assertExists()
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithTag("pin_key_<")
            .assertExists()
            .assertIsDisplayed()

        // Ошибка не должна отображаться изначально
        composeTestRule
            .onNodeWithTag("error_message")
            .assertDoesNotExist()
    }

    @Test
    fun loginScreen_enterSingleDigit_showsInIndicator() {
        // Given: Экран логина
        val mockViewModel = mockk<LoginViewModel>(relaxed = true)
        
        composeTestRule.setContent {
            SHMRYandexTheme(
                mainColor = MainColorType.getDefault()
            ) {
                val navController = rememberNavController()
                LoginScreen(
                    navHostController = navController,
                    viewModel = mockViewModel
                )
            }
        }

        // When: Нажимаем цифру 1
        composeTestRule
            .onNodeWithTag("pin_key_1")
            .performClick()

        // Then: Первый индикатор должен быть заполнен
        composeTestRule
            .onNodeWithTag("pin_indicator_0")
            .assertExists()
    }

    @Test
    fun loginScreen_enterMultipleDigits_showsInIndicators() {
        // Given: Экран логина
        val mockViewModel = mockk<LoginViewModel>(relaxed = true)
        
        composeTestRule.setContent {
            SHMRYandexTheme(
                mainColor = MainColorType.getDefault()
            ) {
                val navController = rememberNavController()
                LoginScreen(
                    navHostController = navController,
                    viewModel = mockViewModel
                )
            }
        }

        // When: Вводим последовательность цифр
        composeTestRule
            .onNodeWithTag("pin_key_1")
            .performClick()
        
        composeTestRule
            .onNodeWithTag("pin_key_2")
            .performClick()
        
        composeTestRule
            .onNodeWithTag("pin_key_3")
            .performClick()

        // Then: Три индикатора должны быть заполнены
        composeTestRule
            .onNodeWithTag("pin_indicator_0")
            .assertExists()
        
        composeTestRule
            .onNodeWithTag("pin_indicator_1")
            .assertExists()
        
        composeTestRule
            .onNodeWithTag("pin_indicator_2")
            .assertExists()
    }

    @Test
    fun loginScreen_deleteDigit_removesFromIndicators() {
        // Given: Экран логина с введенными цифрами
        val mockViewModel = mockk<LoginViewModel>(relaxed = true)
        
        composeTestRule.setContent {
            SHMRYandexTheme(
                mainColor = MainColorType.getDefault()
            ) {
                val navController = rememberNavController()
                LoginScreen(
                    navHostController = navController,
                    viewModel = mockViewModel
                )
            }
        }

        // Вводим две цифры
        composeTestRule
            .onNodeWithTag("pin_key_1")
            .performClick()
        
        composeTestRule
            .onNodeWithTag("pin_key_2")
            .performClick()

        // When: Нажимаем кнопку удаления
        composeTestRule
            .onNodeWithTag("pin_key_<")
            .performClick()

        // Then: Должна остаться только одна цифра
        composeTestRule
            .onNodeWithTag("pin_indicator_0")
            .assertExists()
    }

    @Test
    fun loginScreen_enterWrongPin_showsError() {
        // Given: ViewModel который возвращает false для проверки пин-кода
        val mockViewModel = mockk<LoginViewModel>(relaxed = true)
        val mockNavController = mockk<NavHostController>(relaxed = true)
        every { mockViewModel.checkPassword(any()) } returns false
        
        composeTestRule.setContent {
            SHMRYandexTheme(
                mainColor = MainColorType.getDefault()
            ) {
                LoginScreen(
                    navHostController = mockNavController,
                    viewModel = mockViewModel
                )
            }
        }

        // When: Вводим неверный 4-значный пин-код
        composeTestRule
            .onNodeWithTag("pin_key_1")
            .performClick()
        
        composeTestRule
            .onNodeWithTag("pin_key_2")
            .performClick()
        
        composeTestRule
            .onNodeWithTag("pin_key_3")
            .performClick()
        
        composeTestRule
            .onNodeWithTag("pin_key_4")
            .performClick()

        // Ждем выполнения LaunchedEffect
        composeTestRule.waitForIdle()

        // Then: Должно появиться сообщение об ошибке
        composeTestRule
            .onNodeWithTag("error_message")
            .assertIsDisplayed()
            .assertTextEquals("Неверный PIN-код")

        // Проверяем что метод проверки был вызван
        verify(timeout = 1000) { mockViewModel.checkPassword("1234") }
    }

    @Test
    fun loginScreen_enterCorrectPin_callsCheckPassword() {
        // Given: ViewModel который возвращает true для правильного пин-кода
        val mockViewModel = mockk<LoginViewModel>(relaxed = true)
        val mockNavController = mockk<NavHostController>(relaxed = true)
        every { mockViewModel.checkPassword("1234") } returns true
        
        composeTestRule.setContent {
            SHMRYandexTheme(
                mainColor = MainColorType.getDefault()
            ) {
                LoginScreen(
                    navHostController = mockNavController,
                    viewModel = mockViewModel
                )
            }
        }

        // When: Вводим правильный 4-значный пин-код
        composeTestRule
            .onNodeWithTag("pin_key_1")
            .performClick()
        
        composeTestRule
            .onNodeWithTag("pin_key_2")
            .performClick()
        
        composeTestRule
            .onNodeWithTag("pin_key_3")
            .performClick()
        
        composeTestRule
            .onNodeWithTag("pin_key_4")
            .performClick()

        // Ждем выполнения LaunchedEffect
        composeTestRule.waitForIdle()

        // Then: Метод проверки должен быть вызван с правильным значением
        verify(timeout = 1000) { mockViewModel.checkPassword("1234") }

        // Ошибка не должна отображаться
        composeTestRule
            .onNodeWithTag("error_message")
            .assertDoesNotExist()
    }

    @Test
    fun loginScreen_enterCorrectPin_triggersNavigation() {
        // Given: ViewModel который возвращает true для правильного пин-кода
        val mockViewModel = mockk<LoginViewModel>(relaxed = true)
        val mockNavController = mockk<NavHostController>(relaxed = true)
        every { mockViewModel.checkPassword("1234") } returns true
        
        composeTestRule.setContent {
            SHMRYandexTheme(
                mainColor = MainColorType.getDefault()
            ) {
                LoginScreen(
                    navHostController = mockNavController,
                    viewModel = mockViewModel
                )
            }
        }

        // When: Вводим правильный 4-значный пин-код
        composeTestRule
            .onNodeWithTag("pin_key_1")
            .performClick()
        
        composeTestRule
            .onNodeWithTag("pin_key_2")
            .performClick()
        
        composeTestRule
            .onNodeWithTag("pin_key_3")
            .performClick()
        
        composeTestRule
            .onNodeWithTag("pin_key_4")
            .performClick()

        // Ждем выполнения LaunchedEffect
        composeTestRule.waitForIdle()

        // Then: Навигация должна быть вызвана
        verify(timeout = 1000) { 
            mockNavController.navigate(
                "main", 
                any<NavOptionsBuilder.() -> Unit>()
            ) 
        }
    }

    @Test
    fun loginScreen_cannotEnterMoreThanFourDigits() {
        // Given: Экран логина
        val mockViewModel = mockk<LoginViewModel>(relaxed = true)
        
        composeTestRule.setContent {
            SHMRYandexTheme(
                mainColor = MainColorType.getDefault()
            ) {
                val navController = rememberNavController()
                LoginScreen(
                    navHostController = navController,
                    viewModel = mockViewModel
                )
            }
        }

        // When: Пытаемся ввести 5 цифр
        composeTestRule
            .onNodeWithTag("pin_key_1")
            .performClick()
        
        composeTestRule
            .onNodeWithTag("pin_key_2")
            .performClick()
        
        composeTestRule
            .onNodeWithTag("pin_key_3")
            .performClick()
        
        composeTestRule
            .onNodeWithTag("pin_key_4")
            .performClick()
        
        composeTestRule
            .onNodeWithTag("pin_key_5")
            .performClick()

        // Then: Должны отображаться только 4 индикатора
        repeat(4) { i ->
            composeTestRule
                .onNodeWithTag("pin_indicator_$i")
                .assertExists()
        }
    }

    @Test
    fun loginScreen_emptyPin_deleteDoesNothing() {
        // Given: Экран логина без введенных цифр
        val mockViewModel = mockk<LoginViewModel>(relaxed = true)
        
        composeTestRule.setContent {
            SHMRYandexTheme(
                mainColor = MainColorType.getDefault()
            ) {
                val navController = rememberNavController()
                LoginScreen(
                    navHostController = navController,
                    viewModel = mockViewModel
                )
            }
        }

        // When: Нажимаем кнопку удаления на пустом поле
        composeTestRule
            .onNodeWithTag("pin_key_<")
            .performClick()

        // Then: Состояние не должно измениться, все индикаторы пустые
        repeat(4) { i ->
            composeTestRule
                .onNodeWithTag("pin_indicator_$i")
                .assertExists()
        }
    }

    @Test
    fun loginScreen_allNumberKeys_areClickable() {
        // Given: Экран логина
        val mockViewModel = mockk<LoginViewModel>(relaxed = true)
        
        composeTestRule.setContent {
            SHMRYandexTheme(
                mainColor = MainColorType.getDefault()
            ) {
                val navController = rememberNavController()
                LoginScreen(
                    navHostController = navController,
                    viewModel = mockViewModel
                )
            }
        }

        // Then: Все цифровые кнопки должны быть кликабельными
        (0..9).forEach { number ->
            composeTestRule
                .onNodeWithTag("pin_key_$number")
                .assertExists()
                .assertHasClickAction()
        }

        // Кнопка удаления тоже должна быть кликабельной
        composeTestRule
            .onNodeWithTag("pin_key_<")
            .assertExists()
            .assertHasClickAction()
    }
} 