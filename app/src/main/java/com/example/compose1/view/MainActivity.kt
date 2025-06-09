package com.example.compose1.view

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.compose1.app_navigation.AppNavigation
import com.example.compose1.extensions.setEdgeToEdge
import com.example.compose1.screens.Screen
import com.example.compose1.screens.employees.EmployeeList
import com.example.compose1.screens.login.LoginScreen
import com.example.compose1.ui.theme.DeepsCompose1Theme
import com.example.compose1.viewmodel.EmployeeListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: EmployeeListViewModel by viewModels()

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // old way of creating the viewModel instance
        /*val viewModel: EmployeeListViewModel =
            ViewModelProvider(this)[EmployeeListViewModel::class.java]*/

        enableEdgeToEdge()
        setContent {

            DeepsCompose1Theme {
                val navController = rememberNavController()
                val currentBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = currentBackStackEntry?.destination?.route

                val view = LocalView.current
                val window = (view.context as Activity).window

                // Change the Status bar behavior based on route
                LaunchedEffect(currentRoute) {
                    when (currentRoute) {
                        Screen.Login.route -> {
                            setEdgeToEdge(true)
                            // Set status bar background color
                            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
                                @Suppress("DEPRECATION")
                                window.statusBarColor = Color(0xFFFF9800).toArgb()
                            }

                            // Ensure icons are white (light content)
                            WindowCompat.getInsetsController(
                                window,
                                view
                            ).isAppearanceLightStatusBars = false
                        }

                        else -> {
                            setEdgeToEdge(false)

                            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
                                @Suppress("DEPRECATION")
                                window.statusBarColor = Color.White.toArgb()
                            }

                            WindowCompat.getInsetsController(
                                window,
                                view
                            ).isAppearanceLightStatusBars = true
                        }
                    }
                }

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {

                        if (currentRoute == Screen.EmployeeList.route) {
                            CenterAlignedTopAppBar(
                                title = {
                                    Text(
                                        text = "Jetpack Compose",
                                        fontWeight = FontWeight.Bold,
                                        color = Color.Black,
                                        textAlign = TextAlign.Center,
                                        style = MaterialTheme.typography.headlineSmall
                                    )
                                },
                                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                                    containerColor = Color(0xFFFF9800)
                                )
                            )
                        }

                    }) { innerPadding ->

                    Box(modifier = Modifier.padding(innerPadding)) {
                        AppNavigation(navController = navController)
                    }
                }
            }
        }
    }
}

