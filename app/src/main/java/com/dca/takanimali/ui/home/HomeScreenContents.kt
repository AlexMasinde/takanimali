package com.dca.takanimali.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.dca.takanimali.ui.auth.AuthViewModel
import com.dca.takanimali.ui.home.components.Header
import com.dca.takanimali.ui.home.components.HomeNavigation


@Composable
fun HomeScreenContents(
    navController: NavController,
    authViewModel: AuthViewModel = hiltViewModel(),
) {

    val modifier: Modifier = Modifier

    val authenticatedUser by authViewModel.authenticatedUser.collectAsState()
    val userName = authenticatedUser.details?.name
    val roleId = authenticatedUser.details?.role_id
    val token = authenticatedUser.details?.access_token
    Column {
        Box(modifier.padding(bottom = 16.dp)) {
            Header(userName)
        }
        Box(modifier.padding(top = 0.dp)) {
            HomeNavigation(navController, roleId)
        }
    }

}