package com.example.takanimali.ui.verify

import com.example.takanimali.ui.register.RegisterViewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.takanimali.ui.reusablecomponents.*
import com.example.takanimali.ui.theme.Grey

@Composable
fun VerifyScreenContent(
    navController: NavController,
    registerViewModel: RegisterViewModel = hiltViewModel()
) {
    val verificationCode by registerViewModel.verificationCode
    val verifyUiState by registerViewModel.verifyUiState.collectAsState()
    val resendCodeMessage by registerViewModel.resendCodeMessage
    val verificationCodeError = verifyUiState.codeError
    val httpAuthError = verifyUiState.HTTPAuthError
    val iOAuthError = verifyUiState.IOAuthError

    val showCodeMessage = resendCodeMessage.isNotEmpty()

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
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Heading(
                title = "Verify Email",
                subtitle = "Enter verification code sent to your email"
            )
            Divider(thickness = 48.dp, color = Grey)
            Input(
                value = verificationCode,
                placeholder = "Verification Code",
                onUserValue = { registerViewModel.onVerificationCodeChange(it) },
                label = "Verification Code",
                type = "Number"
            )
            verificationCodeError?.let {
                ErrorText(error = verificationCodeError)
            }

            Box(Modifier.padding(start = 21.dp, end = 21.dp, top = 10.dp)) {
                PrimaryButton(
                    buttonText = "Verify",
                    disabled = false,
                    onClick = { registerViewModel.verifyCode() },
                    loadingState = false
                )
            }
            Box(Modifier.padding(start = 21.dp, end = 21.dp, top = 10.dp)) {
                PrimaryButton(
                    buttonText = "Back",
                    disabled = false,
                    onClick = { navController.popBackStack() },
                    loadingState = false
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
            if (showCodeMessage) {
                Box(modifier = Modifier.padding(top = 10.dp)) {
                    ErrorText(error = resendCodeMessage)
                }
            }

        }
    }
}