package com.example.takanimali.ui.reusablecomponents

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.takanimali.ui.theme.Grey

@Composable
fun Heading(title: String, subtitle: String){
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 21.dp)) {
        Text(text = title, style = MaterialTheme.typography.h2)
        Divider(thickness = 8.dp, color = Grey)
        Text(text = subtitle, style = MaterialTheme.typography.subtitle1)
    }
}