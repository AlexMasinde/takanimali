package com.example.takanimali.ui.profile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.takanimali.ui.auth.AuthViewModel
import com.example.takanimali.ui.profile.components.ProfileHeader
import com.example.takanimali.ui.profile.components.ProfileList
import com.example.takanimali.ui.profile.components.ProfileLogout
import com.example.takanimali.ui.profile.components.ProfileTitleText
import com.example.takanimali.ui.reusablecomponents.PageHeader
import com.example.takanimali.ui.theme.Grey
import com.example.takanimali.ui.theme.TakaNiMaliTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun ProfileScreenContents(navController: NavController, authViewModel: AuthViewModel, modifier: Modifier = Modifier) {

    val authenticatedUser by authViewModel.authenticatedUser.collectAsState()
    val userName = authenticatedUser.details?.name
    val location = authenticatedUser.details?.location

    val systemUiController = rememberSystemUiController()
    DisposableEffect(systemUiController) {
        systemUiController.setStatusBarColor(Grey)
        onDispose {  }
    }
     Column {
         PageHeader("Profile", navController)
         Box(modifier.padding(vertical = 60.dp)) {
             ProfileHeader(userName, location)
         }
         Box (modifier.padding(horizontal = 24.dp, vertical = 12.dp)){
             ProfileTitleText(text = "Select")
         }
         ProfileList(navController)
         ProfileLogout(navController, authViewModel)

     }
}

