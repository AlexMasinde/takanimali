package com.example.takanimali.ui.register

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.navigation.NavController
import com.example.takanimali.data.VerificationResource
import com.example.takanimali.ui.SuccessPage
import com.example.takanimali.ui.loading.LoadingScreen
import com.example.takanimali.ui.theme.Primary
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun VerifyCodeScreen(navController: NavController, registerViewModel: RegisterViewModel) {
    val systemUiController = rememberSystemUiController()

    DisposableEffect(systemUiController) {
        systemUiController.setStatusBarColor(Primary)
        onDispose { }
    }
    when (registerViewModel.verifiedState) {
        is VerificationResource.Loading -> LoadingScreen()
        is VerificationResource.Verified -> SuccessPage(navController, "home", "Email verified successfully! Proceed to login")
        is VerificationResource.NotVerified -> RegisterVerifyCode(registerViewModel)
    }

}