package com.dca.takanimali.ui.collection.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp


@Composable
fun CollectionScreenTop(
    @DrawableRes icon: Int,
    amount: String,
    text: String,
    modifier: Modifier = Modifier
) {
    Surface(
        elevation = 3.dp,
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start,
            modifier = modifier
                .height(140.dp)
                .width(156.dp)
                .padding(start = 16.dp)
        ) {
            Image(
                painter = painterResource(icon),
                contentDescription = "Icon"
            )
            Text(
                text = amount,
                style = MaterialTheme.typography.h4,
                modifier = modifier.padding(top = 12.dp)
            )
            Text(
                text = text,
                style = MaterialTheme.typography.subtitle2,
                modifier = modifier.padding(top = 4.dp)
            )
        }
    }
}
