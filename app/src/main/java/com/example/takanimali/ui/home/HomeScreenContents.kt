package com.example.takanimali.ui.home

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.takanimali.model.AuthModel
import com.example.takanimali.ui.auth.AuthViewModel
import com.example.takanimali.ui.home.components.Header
import com.example.takanimali.ui.home.components.HomeNavigation
import com.example.takanimali.ui.theme.TakaNiMaliTheme


@Composable
fun HomeScreenContents(navController: NavController, authViewModel: AuthViewModel, modifier: Modifier = Modifier) {

    val authenticatedUser by authViewModel.authenticatedUser.collectAsState()
    val userName = authenticatedUser.details?.name
    val roleId = authenticatedUser.details?.role_id
    val token = authenticatedUser.details?.access_token
    Log.d("User name", "$userName")
    Log.d("User token", "$token")
    Column {
        Box (modifier.padding(bottom = 16.dp)) {
            Header(userName)
        }
        Box(modifier.padding(top = 0.dp)) {
            HomeNavigation(navController, roleId)
        }
    }

}