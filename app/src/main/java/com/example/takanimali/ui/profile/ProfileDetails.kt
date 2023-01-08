package com.example.takanimali.ui.profile

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.takanimali.ui.auth.AuthViewModel
import com.example.takanimali.ui.profile.components.ProfileDetailsCard
import com.example.takanimali.ui.reusablecomponents.PageHeader

@Composable
fun ProfileDetails(
    navController: NavController,
    modifier: Modifier = Modifier,
    authViewModel: AuthViewModel = hiltViewModel()
) {

    val authenticatedUser by authViewModel.authenticatedUser.collectAsState()

//    val pageIcon = painterResource(id = R.drawable.profile_edit)
    Column(modifier.verticalScroll(enabled = true, state = ScrollState(0))) {
        PageHeader(text = "My Profile", navController)
        ProfileDetailsCard(title = "Full Name", text = authenticatedUser.details?.name)
        ProfileDetailsCard(title = "Email", text = authenticatedUser.details?.email)
        ProfileDetailsCard(title = "Phone Number", text = authenticatedUser.details?.phone_number)
        ProfileDetailsCard(title = "Location", text = authenticatedUser.details?.location)
        ProfileDetailsCard(title = "Zone", text = authenticatedUser.details?.zone)
        ProfileDetailsCard(title = "Block", text = authenticatedUser.details?.block)
    }
}