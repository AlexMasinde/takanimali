package com.example.takanimali.ui.changepassword

import android.graphics.Paint.Align
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.takanimali.R
import com.example.takanimali.ui.reusablecomponents.PageHeader
import com.example.takanimali.ui.reusablecomponents.PasswordInput
import com.example.takanimali.ui.theme.Grey
import com.example.takanimali.ui.theme.White
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun ChangePasswordScreen(navController: NavController, modifier: Modifier = Modifier) {
    val systemUiController = rememberSystemUiController()
    DisposableEffect(systemUiController) {
        systemUiController.setStatusBarColor(Grey)
        onDispose {  }
    }
    Column() {
        PageHeader(text = "", navController)
        Box(
            modifier
                .padding(start = 24.dp, top = 24.dp, end = 24.dp, bottom = 32.dp)
                .fillMaxWidth()
        ) {
            Column(modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = stringResource(R.string.change_password_title),
                    style = MaterialTheme.typography.h1
                )
                Divider(thickness = 8.dp, color = White)
                Text(
                    text = stringResource(R.string.change_password_subtitle),
                    style = MaterialTheme.typography.subtitle1
                )
            }
        }
        PasswordInput(
            value = "",
            placeholder = "Enter new password",
            onUserValue = { TODO() },
            label = "New Password",
        )

        PasswordInput(
            value = "",
            placeholder = "Confirm password",
            onUserValue = { TODO() },
            label = "Confirm Password",
        )

    }
}