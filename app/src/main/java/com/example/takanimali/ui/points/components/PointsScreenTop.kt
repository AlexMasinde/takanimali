package com.example.takanimali.ui.points.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.takanimali.ui.theme.TakaNiMaliTheme

@Composable
fun PointsScreenTop(modifier: Modifier = Modifier) {
    val waste = "75 kgs"
    val points = "750 "
    Column(
        modifier
            .padding(horizontal = 24.dp, vertical = 8.dp),
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp)
        ) {
            ValueContainer("Current Collection", waste)
            ValueContainer("Available Taka points", points)
        }
    }
}

