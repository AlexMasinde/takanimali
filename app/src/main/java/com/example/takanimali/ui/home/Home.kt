package com.example.takanimali.ui.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.takanimali.data.AuthResource
import com.example.takanimali.ui.auth.AuthViewModel
import com.example.takanimali.ui.auth.Login
import com.example.takanimali.ui.loading.LoadingScreen
import com.example.takanimali.ui.theme.Grey
import com.example.takanimali.ui.theme.Primary
import com.example.takanimali.ui.utils.isOnline
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@RequiresApi(Build.VERSION_CODES.M)
@Composable
fun Home(
    navController: NavController,
    authViewModel: AuthViewModel = hiltViewModel(),
) {
    val systemUiController = rememberSystemUiController()

    DisposableEffect(systemUiController) {
        systemUiController.setStatusBarColor(Primary)
        onDispose { }
    }

    val context = LocalContext.current

    val userConnected = isOnline(context)

    LaunchedEffect(userConnected){
        if(userConnected) {
            authViewModel.getUserFromDatabase()
        }
    }


    Surface(
        color = Grey,
        modifier = Modifier
            .fillMaxSize()
    ) {
        when (authViewModel.userState) {
            is AuthResource.Loading -> LoadingScreen()
            is AuthResource.NoUser -> Login(navController)
            is AuthResource.Success -> HomeScreenContents(navController)

        }
    }
}

