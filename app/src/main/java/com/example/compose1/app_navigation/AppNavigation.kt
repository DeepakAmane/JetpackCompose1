package com.example.compose1.app_navigation

import android.annotation.SuppressLint
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.compose1.model.employee.Employee
import com.example.compose1.screens.Screen
import com.example.compose1.screens.employees.EmployeeDetailScreen
import com.example.compose1.screens.employees.EmployeeList
import com.example.compose1.screens.login.LoginScreen
import com.example.compose1.viewmodel.employee.EmployeeListViewModel
import com.google.accompanist.navigation.animation.AnimatedNavHost

@ExperimentalAnimationApi
@SuppressLint("UnrememberedGetBackStackEntry")
@Composable
fun AppNavigation(navController: NavHostController) {

    AnimatedNavHost(
        navController = navController,
        startDestination = Screen.Login.route,
        enterTransition = { slideInHorizontally(initialOffsetX = { 1000 }) },
        exitTransition = { slideOutHorizontally(targetOffsetX = { -1000 }) },
        popEnterTransition = { slideInHorizontally(initialOffsetX = { -1000 }) },
        popExitTransition = { slideOutHorizontally(targetOffsetX = { 1000 }) }
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
            EmployeeList(viewModel = viewModel, navController = navController)
        }

        composable(Screen.EmployeeDetail.route) {
            val employee =
                navController.previousBackStackEntry?.savedStateHandle?.get<Employee>("employee")

            if (employee != null) {
                EmployeeDetailScreen(employee = employee)
            } else {
                Text(
                    "Employee not found",
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(Alignment.Center),
                    color = Color.Red
                )
            }
        }
    }
}