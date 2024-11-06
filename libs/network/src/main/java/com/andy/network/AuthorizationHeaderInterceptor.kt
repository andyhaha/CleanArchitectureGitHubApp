package com.andy.network

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class AuthorizationHeaderInterceptor : Interceptor {
    companion object {
        private const val GITHUB_TOKEN = "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"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        val newRequest: Request = originalRequest.newBuilder()
            .header("authorization", "Bearer $GITHUB_TOKEN")
            .build()

        return chain.proceed(newRequest)
    }
}