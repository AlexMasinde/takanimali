package com.example.takanimali.ui.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.takanimali.R
import com.example.takanimali.ui.theme.LightBackground
import com.example.takanimali.ui.theme.Primary
import com.example.takanimali.ui.theme.White

@Composable
fun Header(name: String?, modifier: Modifier = Modifier) {
    Row(
        modifier
            .background(
                color = Primary,
                shape = RoundedCornerShape(bottomStart = 2.dp, bottomEnd = 42.dp)
            ).fillMaxWidth()
    ) {
        Box(
            modifier
                .background(color = LightBackground, shape = CircleShape)
                .height(48.dp)
                .width(48.dp),
            contentAlignment = Alignment.Center,
        ) {

        }
        Column(modifier.padding(start = 11.dp, top = 24.dp, bottom = 24.dp)) {
            Text(
                text = stringResource(R.string.home_header_text),
                style = MaterialTheme.typography.subtitle2,
                color = White,
                modifier = modifier.alpha(0.48F)
            )
            Text(text = name ?: "", style = MaterialTheme.typography.h2, color = White)
        }
    }
}

