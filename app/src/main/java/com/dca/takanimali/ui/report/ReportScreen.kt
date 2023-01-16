package com.dca.takanimali.ui.report

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.dca.takanimali.data.ReportResource
import com.dca.takanimali.ui.reusablecomponents.SuccessPage
import com.dca.takanimali.ui.auth.AuthViewModel
import com.dca.takanimali.ui.loading.LoadingScreen
import com.dca.takanimali.ui.theme.Grey
import com.dca.takanimali.ui.theme.Primary
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun ReportScreen(
    navController: NavController,
    reportViewModel: ReportViewModel = hiltViewModel(),
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
        when (reportViewModel.reportState) {
            is ReportResource.Loading -> LoadingScreen()
            is ReportResource.NotReported -> ReportContents(navController)
            is ReportResource.Reported -> SuccessPage(
                navController,
                "report",
                "Waste sighting reported successfully"
            )
        }
    }
}