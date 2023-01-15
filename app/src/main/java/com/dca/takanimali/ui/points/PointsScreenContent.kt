package com.dca.takanimali.ui.points

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.dca.takanimali.R
import com.dca.takanimali.ui.points.components.HistoryListItem
import com.dca.takanimali.ui.points.components.PointsScreenTop
import com.dca.takanimali.ui.reusablecomponents.ErrorText
import com.dca.takanimali.ui.reusablecomponents.PageHeader
import com.dca.takanimali.ui.reusablecomponents.PrimaryButton
import com.dca.takanimali.ui.reusablecomponents.SpannableText
import com.dca.takanimali.ui.theme.Grey
import com.dca.takanimali.ui.theme.Primary
import com.google.accompanist.systemuicontroller.rememberSystemUiController


@OptIn(ExperimentalMaterialApi::class)
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

    var refreshing by remember { mutableStateOf(false) }

    fun refreshingFunction() {
        refreshing = true
        pointsViewModel.retry()
        refreshing = false
    }

    val refreshState =
        rememberPullRefreshState(refreshing, onRefresh = { refreshingFunction() })

    Box(modifier.pullRefresh(refreshState)) {
        LazyColumn(modifier.fillMaxWidth()) {
            item {
                PageHeader(text = "Taka Points", navController, "home")
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
                    Box(modifier.padding(start = 24.dp, end = 24.dp, top = 0.dp, bottom = 10.dp)) {
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
                            .padding(top = 2.dp, bottom = 12.dp, start = 24.dp)
                            .fillMaxWidth()

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

                    }
                }
            }
            if (redeemHistoryAvailable) {
                items(redeemHistory) { listItem ->
                    HistoryListItem(listItem)
                }
            }
        }
        PullRefreshIndicator(
            refreshing,
            refreshState,
            modifier = modifier.align(Alignment.TopCenter)
        )
    }
}