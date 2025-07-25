package com.example.shmryandex.app.presentation.login

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.core.presentation.LocalViewModelFactory
import com.example.core.utils.ui.localizedString
import com.example.shmryandex.R
import com.example.shmryandex.app.navigation.Screen
import kotlinx.coroutines.delay

@Composable
fun LoginScreen(
    navHostController: NavHostController,
    viewModel: LoginViewModel = viewModel(factory = LocalViewModelFactory.current)
) {
    var enteredPin by remember { mutableStateOf("") }
    var isError by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = localizedString(R.string.enter_pin_code),
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.testTag("pin_title")
        )

        Spacer(modifier = Modifier.height(24.dp))
        PinIndicators(currentPin = enteredPin, isError = isError)
        Spacer(modifier = Modifier.height(24.dp))

        PinKeyboard(
            onNumberClick = {
                if (enteredPin.length < 4) {
                    isError = false
                    enteredPin += it
                }
            },
            onDelete = {
                if (enteredPin.isNotEmpty()) {
                    enteredPin = enteredPin.dropLast(1)
                }
            }
        )

        LaunchedEffect(enteredPin) {
            if (enteredPin.length == 4) {
                if (viewModel.checkPassword(enteredPin)) {
                    isError = false
                    navHostController.navigate("main") {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                } else {
                    isError = true
                    enteredPin = ""
                }
            }
        }


        if (isError) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Неверный PIN-код",
                color = Color.Red,
                modifier = Modifier.testTag("error_message")
            )
        }
    }
}

@Composable
fun PinKeyboard(
    onNumberClick: (String) -> Unit,
    onDelete: () -> Unit,
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        val keys = listOf(
            listOf("1", "2", "3"),
            listOf("4", "5", "6"),
            listOf("7", "8", "9"),
            listOf("", "0", "<")
        )

        keys.forEach { row ->
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.padding(vertical = 8.dp)
            ) {
                row.forEach { key ->
                    Spacer(modifier = Modifier.width(15.dp))
                    Box(
                        modifier = Modifier
                            .size(80.dp)
                            .testTag("pin_key_$key")
                            .clickable {
                                when (key) {
                                    "<" -> onDelete()
                                    "" -> {}
                                    else -> onNumberClick(key)
                                }
                            }
                            .background(Color.LightGray, CircleShape)
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = key,
                            style = MaterialTheme.typography.titleLarge
                        )
                    }
                    Spacer(modifier = Modifier.width(15.dp))
                }
            }
        }
    }
}

@Composable
fun PinIndicators(
    currentPin: String,
    isError: Boolean,
) {
    Row(modifier = Modifier.fillMaxWidth().testTag("pin_indicators"), horizontalArrangement = Arrangement.Center) {
        repeat(4) { i ->
            val filled = i < currentPin.length
            val borderColor = when {
                isError -> Color.Red
                filled -> MaterialTheme.colorScheme.primary
                else -> Color.Gray
            }

            Box(
                modifier = Modifier
                    .size(35.dp)
                    .testTag("pin_indicator_$i")
                    .border(
                        width = 6.dp,
                        color = borderColor,
                        shape = CircleShape
                    )
                    .padding(4.dp)
            )

            if (i != 3) {
                Spacer(modifier = Modifier.width(12.dp))
            }
        }
    }
}