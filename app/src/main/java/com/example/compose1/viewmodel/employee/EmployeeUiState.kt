package com.example.compose1.viewmodel.employee

import com.example.compose1.model.employee.Employee

sealed class EmployeeUiState {
    data class Success(val employees: List<Employee>) : EmployeeUiState()
    data class Error(val message: String) : EmployeeUiState()
    object Loading : EmployeeUiState()

}