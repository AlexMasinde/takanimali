package com.dca.takanimali.ui.verify

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.dca.takanimali.data.VerificationResource
import com.dca.takanimali.ui.loading.LoadingScreen
import com.dca.takanimali.ui.register.RegisterViewModel
import com.dca.takanimali.ui.reusablecomponents.SuccessPage
import com.dca.takanimali.ui.theme.Primary
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun VerifyScreen(
    navController: NavController,
    registerViewModel: RegisterViewModel = hiltViewModel()
) {
    val systemUiController = rememberSystemUiController()

    DisposableEffect(systemUiController) {
        systemUiController.setStatusBarColor(Primary)
        onDispose { }
    }
    when (registerViewModel.verifiedState) {
        is VerificationResource.Loading -> LoadingScreen()
        is VerificationResource.Verified -> SuccessPage(
            navController,
            "home",
            "Email verified successfully! Proceed to login",
            "Login"
        )
        is VerificationResource.NotVerified -> VerifyScreenContent(navController)
    }
}