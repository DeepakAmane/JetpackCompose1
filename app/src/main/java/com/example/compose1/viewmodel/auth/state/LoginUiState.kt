package com.example.compose1.viewmodel.auth.state

import com.example.compose1.model.auth.LoginResponse

sealed class LoginUiState {
    data object Idle : LoginUiState()
    data object Loading : LoginUiState()
    data class Success(val data: LoginResponse) : LoginUiState()
    data class Error(val message: String) : LoginUiState()
}