package com.example.takanimali.ui

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.takanimali.R
import com.example.takanimali.ui.reusablecomponents.PrimaryButton
import com.example.takanimali.ui.theme.TakaNiMaliTheme

@Composable
fun SuccessPage(
    navController: NavController,
    destination: String,
    message: String,
    modifier: Modifier = Modifier
) {
    val successImage = painterResource(id = R.drawable.sucess_icon)
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painter = successImage, contentDescription = "Success")
        Box(modifier.padding(top = 8.dp)) {
            Text(text = message, style = MaterialTheme.typography.subtitle2)
        }
        Box(modifier.padding(16.dp)) {
            PrimaryButton(
                buttonText = "Continue",
                disabled = false,
                onClick = { navController.navigate(destination) },
                loadingState = false
            )
        }
    }
}