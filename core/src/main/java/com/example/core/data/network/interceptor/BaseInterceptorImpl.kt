package com.example.core.data.network.interceptor

import com.example.core.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Реализация базового перехватчика HTTP-запросов.
 * Добавляет заголовок авторизации с токеном Bearer к каждому исходящему запросу.
 * Токен берется из конфигурации сборки приложения.
 */
class BaseInterceptorImpl: BaseInterceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = BuildConfig.TOKEN

        val request = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer $token")
            .build()

        return chain.proceed(request)
    }
}