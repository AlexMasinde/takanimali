package com.dca.takanimali.ui.points.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.unit.dp
import com.dca.takanimali.model.RedeemHistoryItem
import com.dca.takanimali.ui.theme.BorderColor
import com.dca.takanimali.ui.theme.Primary
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HistoryListItem(redeemHistoryItem: RedeemHistoryItem, modifier: Modifier = Modifier) {
    val points = redeemHistoryItem.total_points
    val date = Instant.parse(redeemHistoryItem.created_at)
    val zonedDate = date.atZone(ZoneId.of("Africa/Nairobi"))
    val dateToDisplay = DateTimeFormatter.ofPattern("dd MMM, yyyy").format(zonedDate)


    Box(
        modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp, horizontal = 24.dp)
            .border(
                BorderStroke(
                    1.dp, SolidColor(
                        BorderColor
                    )
                ), shape = RoundedCornerShape(8.dp)
            )

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
                            text = dateToDisplay,
                            style = MaterialTheme.typography.h6
                        )

                    }
                }
            }
            Column(modifier.padding(end = 14.dp)) {
                Text(
                    text = "$points points",
                    style = MaterialTheme.typography.h5,
                    color = Primary,
                    modifier = modifier.align(alignment = Alignment.End)
                )
            }
        }
    }
}

