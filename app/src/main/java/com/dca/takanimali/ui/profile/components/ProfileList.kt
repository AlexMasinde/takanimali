package com.dca.takanimali.ui.profile.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.dca.takanimali.ui.profile.ProfileListItem

@Composable
fun ProfileList(navController: NavController) {
    val profileListItems = listOf<ProfileListItem>(
        ProfileListItem.Profile,
        ProfileListItem.Collection,
        ProfileListItem.Help
    )
    LazyColumn(){
        items(profileListItems) { profileListItem ->
            ProfileMenuItem(navController, profileListItem)
        }
    }
}