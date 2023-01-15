package com.dca.takanimali.ui.profile.components

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun ProfileTitleText(text: String) {
    Text(text = text, style = MaterialTheme.typography.h6)
}