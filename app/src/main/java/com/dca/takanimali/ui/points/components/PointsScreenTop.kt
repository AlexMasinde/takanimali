package com.dca.takanimali.ui.points.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dca.takanimali.ui.points.PointsViewModel

@Composable
fun PointsScreenTop(
    modifier: Modifier = Modifier,
    pointsViewModel: PointsViewModel = hiltViewModel()
) {
    val pointsTotal by pointsViewModel.pointsTotal.collectAsState()
    val waste = pointsTotal.details?.total_pending_waste ?: "0"
    val points = pointsTotal.details?.total_unredeemed_points ?: "0"
    Column(
        modifier
            .padding(horizontal = 24.dp, vertical = 8.dp),
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp)
        ) {
            ValueContainer("Available waste to redeem", "$waste Kgs")
            ValueContainer("Available Taka points", "$points")
        }
    }
}

