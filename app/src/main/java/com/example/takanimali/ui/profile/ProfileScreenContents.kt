package com.example.takanimali.ui.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.takanimali.R
import com.example.takanimali.ui.auth.AuthViewModel
import com.example.takanimali.ui.profile.components.ProfileHeader
import com.example.takanimali.ui.profile.components.ProfileList
import com.example.takanimali.ui.profile.components.ProfileLogout
import com.example.takanimali.ui.profile.components.ProfileTitleText
import com.example.takanimali.ui.reusablecomponents.PageHeader
import com.example.takanimali.ui.theme.Grey
import com.example.takanimali.ui.theme.ProfileBottomBorder
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun ProfileScreenContents(
    navController: NavController,
    authViewModel: AuthViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {

    val authenticatedUser by authViewModel.authenticatedUser.collectAsState()
    val userName = authenticatedUser.details?.name

    val uniqueId = authenticatedUser.details?.unique_id

    val uriHandler = LocalUriHandler.current

    val systemUiController = rememberSystemUiController()
    DisposableEffect(systemUiController) {
        systemUiController.setStatusBarColor(Grey)
        onDispose { }
    }
    Column {
        PageHeader("Profile", navController, "home")
        Box(modifier.padding(vertical = 60.dp)) {
            ProfileHeader(userName, uniqueId)
        }
        Box(modifier.padding(horizontal = 24.dp, vertical = 12.dp)) {
            ProfileTitleText(text = "Select")
        }
        ProfileList(navController)
        Column(modifier.padding(horizontal = 24.dp)) {
            Row(
                modifier
                    .fillMaxWidth()
                    .padding(vertical = 24.dp)) {
                Image(
                    painter = painterResource(R.drawable.profile_password),
                    contentDescription = "Change Password"
                )
                Text(
                    text = "Change Password",
                    style = MaterialTheme.typography.caption,
                    modifier =  modifier.clickable(enabled = true) {
                        uriHandler.openUri("https://dcatakanimali.co.ke/forgot-password")
                    }.padding(start = 16.dp)
                )
            }
            Divider(thickness = 1.dp, color = ProfileBottomBorder)
        }
        ProfileLogout(navController, authViewModel)
    }
}

