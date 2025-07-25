package com.example.core.utils.ui

enum class MainColorType(val color: String) {

    GREEN("green"),
    RED("red"),
    YELLOW("yellow");

    companion object {
        fun fromValue(color: String): MainColorType {
            return MainColorType.entries.find { it.color == color } ?: RED
        }

        fun getDefault(): MainColorType = RED
    }
}