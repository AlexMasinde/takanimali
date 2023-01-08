package com.example.takanimali.ui.collect

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.takanimali.data.CollectResource
import com.example.takanimali.ui.reusablecomponents.SuccessPage
import com.example.takanimali.ui.auth.AuthViewModel
import com.example.takanimali.ui.loading.LoadingScreen
import com.example.takanimali.ui.theme.Grey
import com.example.takanimali.ui.theme.Primary
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun CollectScreen(
    navController: NavController,
    collectViewModel: CollectViewModel = hiltViewModel(),
    authViewModel: AuthViewModel = hiltViewModel()
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
        when (collectViewModel.collectState) {
            is CollectResource.Loading -> LoadingScreen()
            is CollectResource.NotCollected -> CollectContent(
                navController,
                collectViewModel,
                authViewModel
            )
            is CollectResource.Collected -> SuccessPage(
                navController,
                "collect",
                "Waste added successfully"
            )
        }
    }
}