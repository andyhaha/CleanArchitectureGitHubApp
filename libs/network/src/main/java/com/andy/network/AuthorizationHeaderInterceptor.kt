package com.andy.network

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class AuthorizationHeaderInterceptor : Interceptor {
    companion object {
        private const val GITHUB_TOKEN = BuildConfig.API_TOKEN
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        val newRequest: Request = originalRequest.newBuilder()
            .header("authorization", "Bearer $GITHUB_TOKEN")
            .build()

        return chain.proceed(newRequest)
    }
}