package com.example.takanimali.ui.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.takanimali.data.AuthResource
import com.example.takanimali.ui.auth.AuthViewModel
import com.example.takanimali.ui.auth.Login
import com.example.takanimali.ui.loading.LoadingScreen
import com.example.takanimali.ui.theme.Grey
import com.example.takanimali.ui.theme.Primary
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun Home(
    authViewModel: AuthViewModel,
    navController: NavController,
    onNavigateToLoginScreen: () -> Unit
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
        when (authViewModel.userState) {
            is AuthResource.Loading -> LoadingScreen()
            is AuthResource.NoUser -> Login(navController, authViewModel)
            is AuthResource.Success -> HomeScreenContents(navController, authViewModel)

        }
    }
}
