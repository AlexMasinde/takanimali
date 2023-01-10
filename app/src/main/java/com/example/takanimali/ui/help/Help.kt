package com.example.takanimali.ui.help

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.takanimali.R
import com.example.takanimali.ui.help.components.Contact
import com.example.takanimali.ui.reusablecomponents.PageHeader

@Composable
fun Help(navController: NavController, modifier: Modifier = Modifier) {
    Surface() {
        PageHeader("Help", navController, "profile")
        Column(
            modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
        ) {
            Column(
                modifier
                    .padding(horizontal = 48.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(R.string.help_title),
                    style = MaterialTheme.typography.h1
                )
                Text(
                    text = stringResource(R.string.help_subtitle),
                    style = MaterialTheme.typography.subtitle1,
                    modifier = modifier.padding(top = 8.dp, bottom = 48.dp)
                )
            }
            Contact("Email", R.string.email)
            Contact("Phone", R.string.phone)
            Contact("Website", R.string.website)
        }
    }
}