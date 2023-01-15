package com.dca.takanimali.ui.profile.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.dca.takanimali.ui.profile.ProfileListItem
import com.dca.takanimali.ui.theme.ProfileBottomBorder

@Composable
fun ProfileMenuItem(navController: NavController, profileListItem: ProfileListItem, modifier: Modifier = Modifier) {
        Column(modifier.padding(horizontal = 24.dp).clickable(enabled = true, onClick = {navController.navigate(profileListItem.navLink)})) {
            Row(
                modifier
                    .fillMaxWidth()
                    .padding(vertical = 24.dp)) {
                Image(
                    painter = painterResource(profileListItem.icon),
                    contentDescription = stringResource(profileListItem.text)
                )
                Text(
                    text = stringResource(id = profileListItem.text),
                    style = MaterialTheme.typography.caption,
                    modifier = modifier.padding(start = 16.dp)
                )
            }
            Divider(thickness = 1.dp, color = ProfileBottomBorder)
        }

}

