package com.dca.takanimali.ui.reusablecomponents

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dca.takanimali.ui.theme.Red

@Composable
fun ErrorText(error: String) {
    Box(modifier = Modifier.padding(horizontal = 21.dp).fillMaxWidth()) {
        Text(text = error, color = Red, style = MaterialTheme.typography.body2)
    }
}