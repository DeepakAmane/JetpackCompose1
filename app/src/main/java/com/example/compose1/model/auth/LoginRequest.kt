package com.example.compose1.model.auth

data class LoginRequest(
    val username: String,
    val password: String,
    val expiresInMins: Int = 30
)
