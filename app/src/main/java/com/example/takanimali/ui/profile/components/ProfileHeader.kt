package com.example.takanimali.ui.profile.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ProfileHead(modifier: Modifier = Modifier) {
    Column() {
        Text(text = "Kenneth Irungu")
        Text(text = "Kalobeyei")
    }
}