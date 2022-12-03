package com.example.takanimali.ui.profile.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.takanimali.ui.theme.ProfileBottomBorder
import com.example.takanimali.ui.theme.TakaNiMaliTheme

@Composable
fun ProfileDetailsCard(title: String, text: String, modifier: Modifier = Modifier) {
    Column(
        modifier
            .padding(horizontal = 24.dp, vertical = 16.dp)
    ) {
        ProfileTitleText(text = title)
        Row(
            modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        ) {
            Text(
                text = text,
                style = MaterialTheme.typography.caption,
            )
        }
        Divider(thickness = 1.dp, color = ProfileBottomBorder)
    }
}
