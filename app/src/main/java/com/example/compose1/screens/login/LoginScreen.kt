package com.example.compose1.screens.login

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import com.example.compose1.R
import com.example.compose1.components.LoadingOverlay
import com.example.compose1.viewmodel.auth.LoginViewModel
import com.example.compose1.viewmodel.auth.state.LoginUiState
import com.example.compose1.viewmodel.employee.EmployeeUiState

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel = hiltViewModel(),
    onLoginSuccess: () -> Unit
) {

    var userName by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val context = LocalContext.current
    val loginState by viewModel.loginState.collectAsStateWithLifecycle()

    LaunchedEffect(loginState) {
        when (loginState) {
            is LoginUiState.Error -> {
                Toast.makeText(
                    context,
                    (loginState as LoginUiState.Error).message,
                    Toast.LENGTH_LONG
                ).show()
            }

            LoginUiState.Idle -> {

            }

            LoginUiState.Loading -> {
            }

            is LoginUiState.Success -> {
                onLoginSuccess()
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFF9800))
            .imePadding(),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        )
        {
            Text(
                "Jetpack Compose",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Serif,
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.fillMaxWidth(),
                color = Color(0xFF8A2BE2),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))

            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(data = R.drawable.jetpack_compose_logo)
                    .crossfade(enable = true)
                    .scale(Scale.FILL)
                    .build(),
                contentDescription = stringResource(id = R.string.login_heading_text)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = stringResource(id = R.string.login_heading_text),
                fontSize = 25.sp,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Serif,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = userName,
                onValueChange = {
                    userName = it
                },
                label = { Text("User Name", color = Color(0xFF8A2BE2)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .defaultMinSize(56.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = password,
                onValueChange = {
                    password = it
                },
                label = { Text("Password", color = Color(0xFF8A2BE2)) },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier
                    .fillMaxWidth()
                    .defaultMinSize(56.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    if (userName.isBlank() || password.isBlank()) {
                        Toast.makeText(
                            context,
                            "Please enter both username and password",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        viewModel.login(userName.trim(), password.trim())
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .align(Alignment.CenterHorizontally),
                shape = RoundedCornerShape(15.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF8A2BE2),
                    contentColor = Color.White

                )
            ) {
                Text(
                    "Login",
                    modifier = Modifier,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,

                    )
            }
        }

        // Show loader in center of screen
        if (loginState is LoginUiState.Loading) {
            LoadingOverlay()
        }

    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,

    name = "Pixel 9 Pro XL Preview",
    device = "spec:width=411dp,height=891dp,dpi=440"
)


@Composable
fun LoginScreenPreview() {
    LoginScreen(onLoginSuccess = {})
}
