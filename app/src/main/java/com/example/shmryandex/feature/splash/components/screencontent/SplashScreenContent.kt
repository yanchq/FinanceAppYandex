package com.example.shmryandex.feature.splash.components.screencontent

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.shmryandex.feature.splash.contract.SplashUIEvent
import com.example.shmryandex.feature.splash.contract.SplashUIState

@Composable
fun SplashScreenContent(
    uiState: SplashUIState,
    sendEvent: (SplashUIEvent) -> Unit
) {
    val composition by rememberLottieComposition(LottieCompositionSpec.Asset("wallet_animation.json"))
    val progress by animateLottieCompositionAsState(
        composition,
        iterations = 1
    )

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        LottieAnimation(
            composition = composition,
            progress = { progress },
            modifier = Modifier.size(220.dp)
        )
    }
}