package com.example.takanimali.ui.points

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.takanimali.data.RedeemHistoryResource
import com.example.takanimali.ui.loading.LoadingScreen
import com.example.takanimali.ui.reusablecomponents.ErrorPage
import com.example.takanimali.ui.theme.Grey

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PointsScreen(
    navController: NavController,
    pointsViewModel: PointsViewModel = hiltViewModel()
) {

    Surface(
        color = Grey,
        modifier = androidx.compose.ui.Modifier
            .fillMaxSize()
    ) {
        when (pointsViewModel.redeemHistoryState) {
            is RedeemHistoryResource.Loading -> LoadingScreen()
            is RedeemHistoryResource.Success -> PointsScreenContent(
                navController,
            )
            is RedeemHistoryResource.Error -> ErrorPage(
                navController,
                "Could not fetch points history! Check your connection and try again",
                { pointsViewModel.retry() },
            )
        }
    }

}