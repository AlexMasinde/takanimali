package com.dca.takanimali.ui.collection

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.dca.takanimali.data.CollectionHistoryResource
import com.dca.takanimali.ui.loading.LoadingScreen
import com.dca.takanimali.ui.reusablecomponents.ErrorPage
import com.dca.takanimali.ui.theme.Grey

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CollectionScreen(
    navController: NavController,
    collectionViewModel: CollectionViewModel = hiltViewModel()
) {


    Surface(
        color = Grey,
        modifier = androidx.compose.ui.Modifier
            .fillMaxSize()
    ) {
        when (collectionViewModel.collectionListState) {
            is CollectionHistoryResource.Loading -> LoadingScreen()
            is CollectionHistoryResource.Success -> CollectionScreenContent(navController)
            is CollectionHistoryResource.Error -> ErrorPage(
                navController,
                "Could not fetch collection history! Check your connection and try again",
                { collectionViewModel.retry() }
            )
        }
    }

}