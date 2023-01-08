package com.example.takanimali.ui.reusablecomponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.takanimali.R
import com.example.takanimali.ui.collection.CollectionViewModel
import com.example.takanimali.ui.reusablecomponents.PrimaryButton
import com.example.takanimali.ui.theme.TakaNiMaliTheme

@Composable
fun ErrorPage(
    navController: NavController,
    message: String,
    retryFunction: () -> Unit,
    modifier: Modifier = Modifier
) {

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(modifier.padding(top = 8.dp)) {
            Text(
                text = message,
                style = MaterialTheme.typography.subtitle2,
                color = Color.Red,
                modifier = modifier
                    .padding(horizontal = 16.dp, vertical = 0.dp)
                    .align(Alignment.Center)
            )
        }
        Box(modifier.padding(16.dp)) {
            PrimaryButton(
                buttonText = "Retry",
                disabled = false,
                onClick = { retryFunction() },
                loadingState = false
            )
        }
        Box(modifier.padding(horizontal = 16.dp, vertical = 2.dp)) {
            PrimaryButton(
                buttonText = "Back",
                disabled = false,
                onClick = { navController.popBackStack() },
                loadingState = false
            )
        }
    }
}