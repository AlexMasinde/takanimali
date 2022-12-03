package com.example.takanimali.ui.points.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.unit.dp
import com.example.takanimali.ui.theme.BorderColor

@Composable
fun ValueContainer(text: String, value: String, modifier: Modifier = Modifier) {
    Box(
        modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .border(
                BorderStroke(
                    1.dp, SolidColor(
                        BorderColor
                    )
                ), shape = RoundedCornerShape(8.dp)
            )
    ) {
        Column(modifier.padding(vertical = 14.dp, horizontal = 12.dp)) {
            Text(text = text, style = MaterialTheme.typography.subtitle2)
            Text(text = value, style = MaterialTheme.typography.h4)
        }
    }
}