package com.dca.takanimali.ui.register

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.dca.takanimali.data.RegisterResource
import com.dca.takanimali.ui.loading.LoadingScreen
import com.dca.takanimali.ui.theme.Grey
import com.dca.takanimali.ui.theme.Primary
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun RegisterScreen(
    navController: NavController,
    registerViewModel: RegisterViewModel = hiltViewModel(),
) {
    val systemUiController = rememberSystemUiController()

    DisposableEffect(systemUiController) {
        systemUiController.setStatusBarColor(Primary)
        onDispose { }
    }



    Surface(
        color = Grey,
        modifier = Modifier
            .fillMaxSize()
    ) {
        when (registerViewModel.registerState) {
            is RegisterResource.Loading -> LoadingScreen()
            is RegisterResource.NotRegistered -> RegisterContent(navController)
            is RegisterResource.Registered -> VerifyCodeScreen(navController)
        }
    }
}