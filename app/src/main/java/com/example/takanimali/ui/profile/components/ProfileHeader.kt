package com.example.takanimali.ui.profile.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun ProfileHeader(
    userName: String?,
    uniqueId: String?,
    modifier: Modifier = Modifier
) {
    val userNameToDisplay = userName ?: "Fetching Name..."
    val uniqueIdToDisplay = uniqueId ?: "Fetching ID..."

    Column(modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = userNameToDisplay, style = MaterialTheme.typography.overline)
        Text(text = uniqueIdToDisplay, style = MaterialTheme.typography.h6)
    }
}

