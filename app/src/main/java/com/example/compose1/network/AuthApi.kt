package com.example.compose1.network

import com.example.compose1.model.auth.LoginRequest
import com.example.compose1.model.auth.LoginResponse
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {

    @POST("auth/login")
    suspend fun login(@Body requestBody: LoginRequest): LoginResponse
}