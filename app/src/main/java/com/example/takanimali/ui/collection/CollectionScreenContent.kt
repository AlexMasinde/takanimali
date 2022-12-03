package com.example.takanimali.ui.collection

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.takanimali.R
import com.example.takanimali.ui.collection.components.CollectionListItem
import com.example.takanimali.ui.collection.components.CollectionScreenTop
import com.example.takanimali.ui.reusablecomponents.PageHeader
import com.example.takanimali.ui.theme.Grey
import com.google.accompanist.systemuicontroller.rememberSystemUiController


@Composable
fun CollectionScreenContent(navController: NavController, modifier: Modifier = Modifier) {

    val systemUiController = rememberSystemUiController()
    DisposableEffect(systemUiController) {
        systemUiController.setStatusBarColor(Grey)
        onDispose { }
    }

    val iconLeft = R.drawable.home_data
    val iconRight = R.drawable.home_points


    Column(modifier.fillMaxSize().verticalScroll(state = ScrollState(0), enabled = true)) {
        PageHeader(text = "History", navController)
        Row(
            modifier
                .fillMaxWidth()
                .padding(start = 24.dp, top = 24.dp, end = 24.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            CollectionScreenTop(iconLeft, "75kgs", "Collected")
            CollectionScreenTop(iconRight, "750", "Taka Points")
        }
        Box(modifier.padding(horizontal = 24.dp, vertical = 12.dp)) {
            Text(text = "Transactions", style = MaterialTheme.typography.h4)
        }
        Column() {
            CollectionListItem()
            CollectionListItem()
            CollectionListItem()
            CollectionListItem()
            CollectionListItem()
            CollectionListItem()
            CollectionListItem()
            CollectionListItem()
            CollectionListItem()
            CollectionListItem()
            CollectionListItem()
            CollectionListItem()
        }
    }
}