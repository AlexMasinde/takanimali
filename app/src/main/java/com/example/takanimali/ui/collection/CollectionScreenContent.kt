package com.example.takanimali.ui.collection

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.takanimali.R
import com.example.takanimali.model.CollectionItem
import com.example.takanimali.ui.collection.components.CollectionListItem
import com.example.takanimali.ui.collection.components.CollectionScreenTop
import com.example.takanimali.ui.reusablecomponents.PageHeader
import com.example.takanimali.ui.theme.Grey
import com.google.accompanist.systemuicontroller.rememberSystemUiController


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CollectionScreenContent(
    navController: NavController,
    collectionList: List<CollectionItem>,
    modifier: Modifier = Modifier,
) {

    val systemUiController = rememberSystemUiController()
    DisposableEffect(systemUiController) {
        systemUiController.setStatusBarColor(Grey)
        onDispose { }
    }

    val iconLeft = R.drawable.home_data
    val iconRight = R.drawable.home_points

    val collectionAvailable = collectionList.isNotEmpty()
    val totalKgs = collectionList.sumOf { it.quantity ?: 0 }
    val  totalToDisplay = "$totalKgs Kg(s)"
    val totalPoints = totalKgs * 50
    val totalPointsToDisplay = totalPoints.toString()

    Column(
        modifier
            .fillMaxSize()
    ) {
        PageHeader(text = "History", navController)
        Row(
            modifier
                .fillMaxWidth()
                .padding(start = 24.dp, top = 24.dp, end = 24.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            CollectionScreenTop(iconLeft, totalToDisplay, "Collected")
            CollectionScreenTop(iconRight, totalPointsToDisplay, "Taka Points")
        }
        Box(modifier.padding(horizontal = 24.dp, vertical = 12.dp)) {
            Text(text = "Transactions", style = MaterialTheme.typography.h4)
        }
       Box {
           if(collectionAvailable) {
               LazyColumn {
                   items(collectionList) { listItem ->
                       CollectionListItem(listItem)
                   }
               }
           }
       }
    }
}