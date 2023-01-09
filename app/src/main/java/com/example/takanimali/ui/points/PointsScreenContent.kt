package com.example.takanimali.ui.points

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.takanimali.R
import com.example.takanimali.ui.points.components.HistoryListItem
import com.example.takanimali.ui.points.components.PointsScreenTop
import com.example.takanimali.ui.reusablecomponents.ErrorText
import com.example.takanimali.ui.reusablecomponents.PageHeader
import com.example.takanimali.ui.reusablecomponents.PrimaryButton
import com.example.takanimali.ui.reusablecomponents.SpannableText
import com.example.takanimali.ui.theme.Grey
import com.example.takanimali.ui.theme.Primary
import com.google.accompanist.systemuicontroller.rememberSystemUiController


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PointsScreenContent(
    navController: NavController,
    pointsViewModel: PointsViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {

    val systemUiController = rememberSystemUiController()
    DisposableEffect(systemUiController) {
        systemUiController.setStatusBarColor(Grey)
        onDispose { }
    }

    val redeemHistory by pointsViewModel.redeemHistory.collectAsState()
    val pointsTotal by pointsViewModel.pointsTotal.collectAsState()
    val redeemError by pointsViewModel.redeemError

    val redeemHistoryAvailable = redeemHistory.isNotEmpty()
    val redeemErrorAvailable = redeemError.isNotBlank()

    val cashTotal = pointsTotal.details?.total_unredeemed_points?.times(30)

    val availablePoints = pointsTotal.details?.total_unredeemed_points

    val noPointsAvailable = availablePoints?.toInt() == 0 || availablePoints == null

    Column(modifier.fillMaxWidth()) {
        PageHeader(text = "Taka Points", navController)
        PointsScreenTop()
        if (!noPointsAvailable) {
            Box(
                modifier
                    .padding(start = 24.dp, top = 0.dp, end = 24.dp, bottom = 12.dp)
                    .fillMaxWidth()
                    .wrapContentWidth(align = Alignment.CenterHorizontally)
            ) {
                SpannableText(
                    mainText = stringResource(R.string.redeem_points_text),
                    spanText = " KES $cashTotal"
                )
            }
        }
        if (!noPointsAvailable) {
            Box(modifier.padding(horizontal = 24.dp)) {
                PrimaryButton(
                    buttonText = "Redeem",
                    disabled = false,
                    onClick = { pointsViewModel.redeemPoints() },
                    loadingState = false
                )
            }
        }
        if (redeemErrorAvailable) {
            Box(
                modifier = Modifier
                    .padding(top = 12.dp)
                    .align(Alignment.CenterHorizontally)
            ) {
                ErrorText(error = redeemError)
            }
        }
        Column {
            if (redeemHistoryAvailable) {
                if (noPointsAvailable) {
                    Box(
                        modifier
                            .padding(start = 24.dp, end = 24.dp, top = 0.dp, bottom = 12.dp)
                            .align(Alignment.CenterHorizontally)
                    ) {
                        Text(
                            text = "Your History",
                            color = Primary,
                            style = MaterialTheme.typography.body1
                        )
                    }
                }
                LazyColumn {
                    items(redeemHistory) { listItem ->
                        HistoryListItem(listItem)
                    }
                }
            }
        }
    }
}