package com.example.takanimali.ui.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.takanimali.data.AuthResource
import com.example.takanimali.ui.reusablecomponents.*
import com.example.takanimali.ui.theme.Grey
import com.example.takanimali.ui.theme.Primary
import com.google.accompanist.systemuicontroller.rememberSystemUiController


@Composable
fun Login(
    navController: NavController,
    authViewModel: AuthViewModel = hiltViewModel()
) {

    val systemUiController = rememberSystemUiController()

    DisposableEffect(systemUiController) {
        systemUiController.setStatusBarColor(Primary)
        onDispose { }
    }

    //View model imports
    val loginFormState by authViewModel.loginFormState
    val loginUiState by authViewModel.loginUiState.collectAsState()
    val userState = authViewModel.userState

    //Potential auth errors
    val emailError = loginUiState.emailError
    val passwordError = loginUiState.passwordError
    val iOAuthError = loginUiState.IOAuthError
    val httpAuthError = loginUiState.HTTPAuthError
    val loading: Boolean = userState is AuthResource.Loading


    //Ui conditionals
    val buttonDisabled = loginUiState.loginUiError || loading

    val uriHandler = LocalUriHandler.current

    Surface(
        modifier = Modifier
            .background(MaterialTheme.colors.background)
            .fillMaxHeight()
    ) {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colors.background)
                .fillMaxWidth()
                .fillMaxHeight(),
//            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Divider(thickness = 16.dp, color = Grey)
            PageHeader(text = "Sign In", navController, "home")
            Divider(thickness = 32.dp, color = Grey)
            Heading(
                title = "Hi, Welcome Back",
                subtitle = "Please enter your email and password"
            )
            Divider(thickness = 48.dp, color = Grey)
            Input(
                value = loginFormState.email,
                placeholder = "Email",
                onUserValue = { authViewModel.onEmailChange(it) },
                label = "Email Address",
                type = "Email"
            )
            emailError?.let {
                ErrorText(error = emailError)
            }
            PasswordInput(
                value = loginFormState.password,
                placeholder = "Password",
                onUserValue = { authViewModel.onPasswordChange(it) },
                label = "Password"
            )
            passwordError?.let {
                ErrorText(error = passwordError)
            }
            Divider(thickness = 8.dp, color = Grey)
            Box(Modifier.padding(horizontal = 21.dp)) {
                PrimaryButton(
                    buttonText = "Sign In",
                    disabled = buttonDisabled,
                    onClick = { authViewModel.login() },
                    loadingState = loading
                )
            }
            Box(modifier = Modifier.padding(top = 10.dp)) {
                iOAuthError?.let {
                    ErrorText(error = iOAuthError)
                }
                httpAuthError?.let {
                    ErrorText(error = httpAuthError)
                }
            }
            Divider(thickness = 8.dp, color = Grey)
            Column(
                modifier = Modifier
                    .padding(horizontal = 21.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally

            ) {
                Box() {
                    Text(
                        text = "Reset Password",
                        style = MaterialTheme.typography.h6,
                        color = Primary,
                        modifier = Modifier.clickable(enabled = true, onClick = {
                            uriHandler.openUri("https://dcatakanimali.co.ke/forgot-password")
                        })
                    )
                }
                Box(modifier = Modifier.padding(top = 8.dp)) {
                    SpannableText(
                        mainText = "Don't have an account? ",
                        spanText = "Register",
                        navController,
                        "register"
                    )
                }
                Box(modifier = Modifier.padding(top = 8.dp)) {
                    SpannableText(
                        mainText = "Have a registration code? ",
                        spanText = "Verify Email",
                        navController,
                        "verify"
                    )
                }
            }
        }

    }
}