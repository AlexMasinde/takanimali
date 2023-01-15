package com.dca.takanimali.ui.collection

import android.app.Activity
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.dca.takanimali.R
import com.dca.takanimali.data.CollectionHistoryResource
import com.dca.takanimali.ui.auth.AuthViewModel
import com.dca.takanimali.ui.collection.components.CollectionListItem
import com.dca.takanimali.ui.collection.components.CollectionScreenTop
import com.dca.takanimali.ui.reusablecomponents.PageHeader
import com.dca.takanimali.ui.reusablecomponents.PrimaryButton
import com.dca.takanimali.ui.theme.Grey
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterialApi::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CollectionScreenContent(
    navController: NavController,
    collectionViewModel: CollectionViewModel = hiltViewModel(),
    authViewModel: AuthViewModel = hiltViewModel()
) {

    val systemUiController = rememberSystemUiController()
    DisposableEffect(systemUiController) {
        systemUiController.setStatusBarColor(Grey)
        onDispose { }
    }

    val activity = (LocalContext.current as? Activity)

    val authenticatedUser by authViewModel.authenticatedUser.collectAsState()

    val modifier: Modifier = Modifier

    val iconLeft = R.drawable.home_data
    val iconRight = R.drawable.home_points

    val collectionList by collectionViewModel.collectionList.collectAsState()


    val collectionAvailable = collectionList.isNotEmpty()
    val totalKgs = collectionList.sumOf { it.quantity?.toInt() ?: 0 }
    val totalToDisplay = "$totalKgs Kg(s)"
    val totalPoints = totalKgs * 50
    val totalPointsToDisplay = totalPoints.toString()

    val refreshing = collectionViewModel.collectionListState is CollectionHistoryResource.Loading
    val refreshScope = rememberCoroutineScope()

    fun refreshFunction() = refreshScope.launch {
        val authenticatedUserToken = authenticatedUser.details?.access_token
        val authenticatedUserId = authenticatedUser.details?.id
        if (authenticatedUserToken != null && authenticatedUserId != null) {
            val authorizationHeader = "Bearer $authenticatedUserToken"
            collectionViewModel.accessCollection(authorizationHeader, authenticatedUserId)
        }
    }

    val refreshState =
        rememberPullRefreshState(refreshing, onRefresh = { refreshFunction() })

    Box(modifier.pullRefresh(refreshState)) {
        LazyColumn(
            modifier
                .fillMaxSize()
                .padding(bottom = 12.dp)
        ) {

            item {
                if (!collectionAvailable) {
                    Box(
                        modifier
                            .fillMaxSize()
                            .padding(top = 100.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Column() {
                            Text(
                                text = "You have not collected any waste! Please collect waste and visit your team leader for recording",
                                modifier.padding(horizontal = 24.dp),
                                style = MaterialTheme.typography.h6,
                                lineHeight = 25.sp
                            )
                            Box(modifier.padding(24.dp)) {
                                PrimaryButton(
                                    buttonText = "Home",
                                    disabled = false,
                                    onClick = { navController.navigate("home") },
                                    loadingState = false
                                )
                            }
                        }
                    }
                }

                if (collectionAvailable) {
                    PageHeader(text = "History", navController, "home")
                    Row(
                        modifier
                            .fillMaxWidth()
                            .padding(start = 24.dp, top = 24.dp, end = 24.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        CollectionScreenTop(iconLeft, totalToDisplay, "Collected")
                        CollectionScreenTop(iconRight, totalPointsToDisplay, "Taka Points")
                    }
                    Box(modifier.padding(horizontal = 24.dp, vertical = 14.dp)) {
                        Text(text = "Transactions", style = MaterialTheme.typography.h4)
                    }
                }
            }

            if (collectionAvailable) {
                items(collectionList) { listItem ->
                    CollectionListItem(listItem)
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


