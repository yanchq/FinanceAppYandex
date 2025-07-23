package com.example.shmryandex.app.data.prefsoptions

import android.Manifest
import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import androidx.annotation.RequiresPermission
import com.example.core.data.haptic.HapticManager
import com.example.core.data.haptic.HapticType
import com.example.shmryandex.BuildConfig
import com.example.shmryandex.app.domain.repository.OptionsPreferencesRepository
import javax.inject.Inject


class HapticManagerImpl @Inject constructor(
    private val context: Context,
    private val optionsPreferencesRepository: OptionsPreferencesRepository
): HapticManager {

    private val vibrator = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        val vibratorManager = context.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
        vibratorManager.defaultVibrator
    } else {
        @Suppress("DEPRECATION")
        context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
    }

    @RequiresPermission(Manifest.permission.VIBRATE)
    private fun lightHaptic() {
        vibrator.vibrate(VibrationEffect.createOneShot(50, VibrationEffect.DEFAULT_AMPLITUDE))
    }

    val build = BuildConfig.VERSION_NAME


    @RequiresPermission(Manifest.permission.VIBRATE)
    private fun mediumHaptic() {
        vibrator.vibrate(VibrationEffect.createOneShot(100, VibrationEffect.DEFAULT_AMPLITUDE))
    }

    @RequiresPermission(Manifest.permission.VIBRATE)
    private fun longHaptic() {
        vibrator.vibrate(VibrationEffect.createOneShot(200, VibrationEffect.DEFAULT_AMPLITUDE))
    }


    override suspend fun triggerHaptic() {
        if (optionsPreferencesRepository.getHapticEnabledState() == true) {
            when (optionsPreferencesRepository.getHapticType()) {
                HapticType.SHORT -> {
                    lightHaptic()
                }
                HapticType.MEDIUM -> {
                    mediumHaptic()
                }
                HapticType.LONG -> {
                    longHaptic()
                }
            }
        }
    }
}