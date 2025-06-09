package com.example.compose1.app_navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.compose1.screens.Screen
import com.example.compose1.screens.employees.EmployeeList
import com.example.compose1.screens.login.LoginScreen
import com.example.compose1.viewmodel.EmployeeListViewModel

@Composable
fun AppNavigation(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = Screen.Login.route
    ) {
        composable(Screen.Login.route) {
            LoginScreen(onLoginSuccess = {
                navController.navigate(Screen.EmployeeList.route) {
                    popUpTo(Screen.Login.route) { inclusive = true }
                }
            })
        }

        composable(Screen.EmployeeList.route) {
            val viewModel: EmployeeListViewModel = hiltViewModel()
            EmployeeList(viewModel = viewModel)
        }
    }
}