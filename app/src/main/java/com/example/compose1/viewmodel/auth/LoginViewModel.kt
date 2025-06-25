package com.example.compose1.viewmodel.auth

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.compose1.model.auth.LoginResponse
import com.example.compose1.repository.AuthRepository
import com.example.compose1.viewmodel.auth.state.LoginUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    application: Application,
    private val authRepository: AuthRepository
) : AndroidViewModel(application) {

    private val _loginState = MutableStateFlow<LoginUiState>(LoginUiState.Idle)
    val loginState: StateFlow<LoginUiState> = _loginState

    fun login(userName: String, password: String) {
        _loginState.value = LoginUiState.Loading

        viewModelScope.launch {
            try {
                val response = authRepository.login(userName, password)
                if (response != null) {
                    _loginState.value = LoginUiState.Success(response)
                } else {
                    _loginState.value = LoginUiState.Error("Invalid login response")
                }

            } catch (e: Exception) {
                _loginState.value = LoginUiState.Error(e.localizedMessage ?: "Unknown error")
            }
        }
    }
}