package com.example.shmryandex.app.data.network.interceptor

import okhttp3.Interceptor

/**
 * Базовый интерфейс для перехватчиков HTTP-запросов.
 * Расширяет OkHttp Interceptor для добавления базовой функциональности.
 */
interface BaseInterceptor: Interceptor {
}