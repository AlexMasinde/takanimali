package com.example.takanimali.ui.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import com.example.takanimali.ui.theme.Grey
import com.example.takanimali.ui.theme.Primary
import com.example.takanimali.ui.theme.White

@Composable
fun BalanceCard(modifier: Modifier = Modifier) {
    Box(
        modifier
            .padding(horizontal = 24.dp, vertical = 24.dp)
            .shadow(elevation = 6.dp)
            .background(color = Primary, shape = RoundedCornerShape(12.dp))
    ) {
        Column(
            modifier
                .padding(vertical = 30.dp, horizontal = 12.dp)
                .fillMaxWidth()

        ) {
            Text(
                text = "Total Collection",
                style = MaterialTheme.typography.button,
                color = Grey,
                modifier = modifier.alpha(0.48F)
            )
            Text(text = "4,500 kgs", style = MaterialTheme.typography.h2, color = White)
        }
    }
}