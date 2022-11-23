package com.example.takanimali.ui.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.takanimali.ui.reusablecomponents.*
import com.example.takanimali.ui.theme.Grey
import com.example.takanimali.ui.theme.Primary


@Composable
fun Login(
    loginViewModel: LoginViewModel = viewModel()
) {
    //View model imports
    val loginFormState by loginViewModel.loginFormState
    val loginUiState by loginViewModel.loginUiState.collectAsState()

    val emailError = loginUiState.emailError
    val passwordError = loginUiState.passwordError


    //Ui conditionals
    val buttonDisabled = false

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
            PageHeader(text = "Sign In")
            Divider(thickness = 32.dp, color = Grey)
            Heading(title = "Hi, Welcome Back", subtitle = "Please enter your email and password")
            Divider(thickness = 48.dp, color = Grey)
            Input(
                value = loginFormState.email,
                placeholder = "Email",
                onUserValue = { loginViewModel.onEmailChange(it) },
                label = "Email Address",
                type = "Email"
            )
            emailError?.let {
                ErrorText(error = emailError)
            }
            PasswordInput(
                value = loginFormState.password,
                placeholder = "Password",
                onUserValue = { loginViewModel.onPasswordChange(it) },
                label = "Password"
            )
            passwordError?.let {
                ErrorText(error = passwordError)
            }
            Divider(thickness = 32.dp, color = Grey)
            PrimaryButton(
                buttonText = "Login",
                disabled = buttonDisabled,
                onClick = { loginViewModel.login() })
            Divider(thickness = 24.dp, color = Grey)
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.padding(horizontal = 21.dp).fillMaxWidth()
            ) {
                Box {
                    Text(
                        text = "Forgot Password?",
                        style = MaterialTheme.typography.h6,
                        color = Primary,
                    )
                }
                Box{
                    SpannableText(mainText = "Need an account? ", spanText = "Sign Up")
                }
            }
        }
    }
}