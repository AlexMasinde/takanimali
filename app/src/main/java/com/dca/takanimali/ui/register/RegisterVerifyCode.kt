package com.dca.takanimali.ui.register

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
import com.dca.takanimali.ui.reusablecomponents.ErrorText
import com.dca.takanimali.ui.reusablecomponents.Heading
import com.dca.takanimali.ui.reusablecomponents.Input
import com.dca.takanimali.ui.reusablecomponents.PrimaryButton
import com.dca.takanimali.ui.theme.Grey

@Composable
fun RegisterVerifyCode(registerViewModel: RegisterViewModel = hiltViewModel()) {
    val verificationCode by registerViewModel.verificationCode
    val verifyUiState by registerViewModel.verifyUiState.collectAsState()
    val resendCodeMessage by registerViewModel.resendCodeMessage
    val verificationCodeError =  verifyUiState.codeError
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
                    buttonText = "Resend Code",
                    disabled = false,
                    onClick = { registerViewModel.resendVerificationCode() },
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
            if(showCodeMessage) {
                Box(modifier = Modifier.padding(top = 10.dp)) {
                    ErrorText(error = resendCodeMessage)
                }
            }

        }
    }
}