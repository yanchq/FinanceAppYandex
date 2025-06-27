package com.example.shmryandex.core.data.network.interceptor

import com.example.shmryandex.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class BaseInterceptorImpl: BaseInterceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = BuildConfig.TOKEN

        val request = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer $token")
            .build()

        return chain.proceed(request)
    }
}