package com.example.takanimali.ui.profile.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.takanimali.ui.profile.ProfileListItem

@Composable
fun ProfileList(navController: NavController, modifier: Modifier = Modifier) {
    val profileListItems = listOf<ProfileListItem>(
        ProfileListItem.Profile,
        ProfileListItem.Collection,
        ProfileListItem.Password,
        ProfileListItem.Help
    )
    LazyColumn(){
        items(profileListItems) { profileListItem ->
            ProfileMenuItem(navController, profileListItem)
        }
    }
}