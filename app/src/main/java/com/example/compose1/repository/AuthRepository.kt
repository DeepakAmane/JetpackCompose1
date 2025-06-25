package com.example.compose1.repository

import com.example.compose1.model.auth.LoginRequest
import com.example.compose1.model.auth.LoginResponse
import com.example.compose1.network.AuthApi
import com.example.compose1.network.EmployeeApi
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val api: AuthApi
) {

    suspend fun login(username: String, password: String): LoginResponse? {
        val response = api.login(LoginRequest(username, password))
        return if (!response.username.isNullOrBlank() && !response.accessToken.isNullOrBlank()) {
            response
        } else {
            null
        }
    }
}