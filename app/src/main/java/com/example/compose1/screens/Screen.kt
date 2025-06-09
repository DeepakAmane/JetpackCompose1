package com.example.compose1.screens

sealed class Screen(val route: String) {
    data object Login : Screen("login")
    data object EmployeeList : Screen("employeeList")
}