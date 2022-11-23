package com.example.takanimali.ui.collection

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.takanimali.R
import com.example.takanimali.ui.reusablecomponents.PageHeader
import com.example.takanimali.ui.theme.Grey
import com.example.takanimali.ui.theme.TakaNiMaliTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController


@Composable
fun CollectionScreenContent(modifier: Modifier = Modifier) {

    val systemUiController = rememberSystemUiController()
    DisposableEffect(systemUiController) {
        systemUiController.setStatusBarColor(Grey)
        onDispose {  }
    }

    val iconLeft = R.drawable.home_data
    val iconRight = R.drawable.home_points


    Column(modifier.fillMaxSize()) {
        PageHeader(text = "History")
        Row(
            modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 24.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            CollectionScreenTop(iconLeft, "\$6.564,34", "Collected")
            CollectionScreenTop(iconRight, "\$4.564,34", "Taka Points")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CollectionScreenContentPreview() {
    TakaNiMaliTheme {
        CollectionScreenContent()
    }
}