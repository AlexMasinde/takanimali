package com.example.takanimali.ui.points

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

@Composable
fun PointsScreen(
    navController: NavController,
    pointsViewModel: PointsViewModel = hiltViewModel()
) {

    val redeemHistory by pointsViewModel.redeemHistory.collectAsState()


    Surface(
        color = Grey,
        modifier = androidx.compose.ui.Modifier
            .fillMaxSize()
    ) {
        when (pointsViewModel.redeemHistoryState) {
            is RedeemHistoryResource.Loading -> LoadingScreen()
            is RedeemHistoryResource.Success -> PointsScreenContent(
                navController,
                redeemHistory,
            )
            is RedeemHistoryResource.Error -> ErrorPage(
                navController,
                "Could not fetch points history! Check your connection and try again",
                { pointsViewModel.retry() },
            )
        }
    }

}