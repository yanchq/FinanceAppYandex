package com.example.history.impl.presentation.analytics.utils

import androidx.compose.ui.graphics.Color
import com.example.history.impl.presentation.analytics.contract.GroupedHistory

fun toPieChartData(categoryStats: List<GroupedHistory>): Map<String, Float> {
    return categoryStats.associate { stat ->
        "${stat.emoji} ${stat.name}" to stat.percentage.toFloat()
    }
}

fun generateColorsHSV(count: Int): List<Color> {
    val colors = mutableListOf<Color>()
    val step = 360f / count
    for (i in 0 until count) {
        val hue = step * i
        val hsv = floatArrayOf(hue, 0.8f, 0.9f) // насыщенность и яркость фиксированы
        val colorInt = android.graphics.Color.HSVToColor(hsv)
        colors.add(Color(colorInt))
    }
    return colors
}