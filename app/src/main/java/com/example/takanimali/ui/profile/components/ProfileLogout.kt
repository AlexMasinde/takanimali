package com.example.takanimali.ui.profile.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.takanimali.ui.auth.AuthViewModel
import com.example.takanimali.ui.theme.SecondaryRed

@Composable
fun ProfileLogout(
    navController: NavController,
    authViewModel: AuthViewModel, modifier: Modifier = Modifier
) {
    fun logout() {
        authViewModel.logout()
        navController.navigate("home")
    }
    Box(
        modifier
            .padding(vertical = 24.dp)
            .fillMaxWidth()
            .wrapContentWidth(Alignment.CenterHorizontally)
            .clickable(enabled = true, onClick = { logout() })
    ) {
        Text(text = "Logout", style = MaterialTheme.typography.caption, color = SecondaryRed)
    }
}