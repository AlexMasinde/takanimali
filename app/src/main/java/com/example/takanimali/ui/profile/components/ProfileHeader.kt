package com.example.takanimali.ui.profile.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun ProfileHeader(userName: String?, location: String?, modifier: Modifier = Modifier) {
    val userNameToDisplay = userName ?: "Fetching Name..."
    val locationToDisplay = location ?: "Fetching Location..."
    Column(modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = userNameToDisplay, style = MaterialTheme.typography.overline)
        Text(text = locationToDisplay, style = MaterialTheme.typography.h6)
    }
}
