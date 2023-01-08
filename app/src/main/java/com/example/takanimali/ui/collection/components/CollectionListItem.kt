package com.example.takanimali.ui.collection.components

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.takanimali.R
import com.example.takanimali.model.CollectionItem
import com.example.takanimali.ui.theme.Primary
import com.example.takanimali.ui.theme.TakaNiMaliTheme
import com.example.takanimali.ui.theme.White
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CollectionListItem(collectionItem: CollectionItem, modifier: Modifier = Modifier) {
    val weightToString = collectionItem.quantity.toString()
    val weightText = "$weightToString Kg(s)"
    val date = Instant.parse(collectionItem.date)
    val zonedDate = date.atZone(ZoneId.of("Africa/Nairobi"))
    val dateToDisplay = DateTimeFormatter.ofPattern("dd MMM, yyyy").format(zonedDate)


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
                        Text(text = collectionItem.waste_type ?: "",
                            style = MaterialTheme.typography.body1)
                        Text(
                            text = collectionItem.location ?: "",
                            style = MaterialTheme.typography.subtitle2,
                        )
                    }
                }
            }
            Column(modifier.padding(end = 14.dp)) {
                Text(text = dateToDisplay,
                    style = MaterialTheme.typography.subtitle2)
                Text(
                    text = weightText,
                    style = MaterialTheme.typography.h5,
                    color = Primary,
                    modifier = modifier.align(alignment = Alignment.End)
                )
            }
        }
    }
}