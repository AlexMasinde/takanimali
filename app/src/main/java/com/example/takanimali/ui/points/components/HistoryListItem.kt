package com.example.takanimali.ui.points.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.takanimali.ui.theme.Primary
import com.example.takanimali.ui.theme.TakaNiMaliTheme
import com.example.takanimali.ui.theme.White


@Composable
fun HistoryListItem(modifier: Modifier = Modifier) {
    Surface(
        modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 7.dp)
            .background(color = White),
        elevation = 3.dp,
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier
                .fillMaxWidth()
                .padding(vertical = 14.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier.padding(start = 14.dp)) {
                Row() {
                    Column(modifier.padding(start = 14.dp)) {
                        Text(
                            text = "14 Aug, 2022",
                            style = MaterialTheme.typography.body1
                        )

                    }
                }
            }
            Column(modifier.padding(end = 14.dp)) {
                Text(
                    text = "14 Aug, 2022",
                    style = MaterialTheme.typography.subtitle2
                )
                Text(
                    text = "30 Kgs",
                    style = MaterialTheme.typography.h5,
                    color = Primary,
                    modifier = modifier.align(alignment = Alignment.End)
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun HistoryListItemPreview() {
    TakaNiMaliTheme {
        HistoryListItem()
    }
}