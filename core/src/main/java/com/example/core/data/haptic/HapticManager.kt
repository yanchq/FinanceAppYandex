package com.example.core.data.haptic

interface HapticManager {

    suspend fun triggerHaptic()
}

enum class HapticType(val value: String) {
    SHORT("short"),
    MEDIUM("medium"),
    LONG("long");

    companion object {
        fun fromValue(value: String): HapticType {
            return values().find { it.value == value } ?: SHORT
        }

        fun getDefault(): HapticType = SHORT
    }
}