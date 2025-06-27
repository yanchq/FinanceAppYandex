package com.example.shmryandex.core.data.network.model

import kotlinx.coroutines.delay
import retrofit2.HttpException

/**
 * Sealed класс для обработки результатов сетевых запросов.
 * Поддерживает три состояния: Success, Error и Loading.
 * Включает механизм автоматического повтора запросов при получении ошибки 500.
 * @param T тип данных, возвращаемых в случае успеха
 */
sealed class Result<out T> {
    data class Success<T>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()
    object Loading : Result<Nothing>()

    companion object {
        private const val MAX_RETRIES = 3
        private const val RETRY_DELAY_MS = 2000L

        suspend fun <T> execute(block: suspend () -> T): Result<T> {
            var retryCount = 0
            while (true) {
                try {
                    return Success(block())
                } catch (e: Exception) {
                    if (e is HttpException && e.code() == 500 && retryCount < MAX_RETRIES) {
                        retryCount++
                        delay(RETRY_DELAY_MS)
                        continue
                    }
                    return Error(e)
                }
            }
        }
    }
} 