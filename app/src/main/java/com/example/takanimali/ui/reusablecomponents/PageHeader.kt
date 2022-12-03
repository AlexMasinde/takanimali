package com.example.takanimali.ui.reusablecomponents

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.takanimali.ui.theme.BorderColor
import com.example.takanimali.R

@Composable
fun PageHeader(text: String, navController: NavController) {
    //SVG import
    val arrowBack = painterResource(id = R.drawable.arrow_back)
    Row(
        modifier = Modifier
            .padding(horizontal = 21.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(85.dp),
        verticalAlignment = Alignment.CenterVertically

    ) {
        Box(
            modifier = Modifier.border(
                BorderStroke(
                    1.dp, SolidColor(
                        BorderColor
                    )
                ), shape = CircleShape
            ).clickable(enabled = true, onClick = {navController.popBackStack()}),
            contentAlignment = Alignment.Center,
        ) {
            Image(
                painter = arrowBack,
                contentDescription = "back arrow",
                modifier = Modifier.padding(
                    start = 19.99.dp,
                    top = 17.dp,
                    end = 21.01.dp,
                    bottom = 17.dp
                )
            )
        }
        Text(
            text = text,
            style = MaterialTheme.typography.h3,
            modifier = Modifier.padding(vertical = 0.dp)
        )
    }
}