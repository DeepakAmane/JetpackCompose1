package com.example.compose1.network

import com.example.compose1.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val originalRequest = chain.request()
        val modifiedRequest = originalRequest.newBuilder()
            .addHeader(
                "X-Master-Key",
                BuildConfig.JSONBIN_API_KEY
            )
            .addHeader("X-Bin-Private","true")
            .build()
        return chain.proceed(modifiedRequest)
    }
}