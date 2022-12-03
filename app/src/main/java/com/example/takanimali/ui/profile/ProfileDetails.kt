package com.example.takanimali.ui.profile

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.takanimali.ui.profile.components.ProfileDetailsCard
import com.example.takanimali.ui.reusablecomponents.PageHeader

@Composable
fun ProfileDetails(navController: NavController, modifier: Modifier = Modifier) {

//    val pageIcon = painterResource(id = R.drawable.profile_edit)
    Column(modifier.verticalScroll(enabled = true, state = ScrollState(0))) {
        PageHeader(text = "My Profile", navController)
        ProfileDetailsCard(title = "Full Name", text = "Alex Masinde")
        ProfileDetailsCard(title = "Email", text = "alekseiwriter@gmail.com")
        ProfileDetailsCard(title = "Phone Number", text = "+254 703 447 147")
        ProfileDetailsCard(title = "Location", text = "Kakuma")
        ProfileDetailsCard(title = "Zone", text = "Kakuma-1")
        ProfileDetailsCard(title = "Block", text = "Block-1")
    }
}