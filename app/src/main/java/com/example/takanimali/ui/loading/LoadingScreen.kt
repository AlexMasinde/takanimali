package com.example.takanimali.ui.loading

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.takanimali.ui.theme.Primary
import com.example.takanimali.ui.theme.TakaNiMaliTheme

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Column(modifier.fillMaxWidth().fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        CircularProgressIndicator(strokeWidth = 5.dp, color = Primary )
    }
}

@Preview(showBackground = true)
@Composable
fun LoadPreview() {
    TakaNiMaliTheme {
        LoadingScreen()
    }
}