package com.example.takanimali.ui.profile.components

import android.app.Activity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.takanimali.ui.auth.AuthViewModel
import com.example.takanimali.ui.collection.CollectionViewModel
import com.example.takanimali.ui.points.PointsViewModel
import com.example.takanimali.ui.theme.SecondaryRed
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun ProfileLogout(
    navController: NavController,
    authViewModel: AuthViewModel = hiltViewModel(),
    collectionViewModel: CollectionViewModel = hiltViewModel(),
    pointsViewModel: PointsViewModel = hiltViewModel()
) {

    val activity = (LocalContext.current as? Activity)

    val modifier: Modifier = Modifier


    fun logout() {
        collectionViewModel.deleteCollectionHistory()
        pointsViewModel.deletePointsCollection()
        collectionViewModel.clearCollectionHistoryData()
        pointsViewModel.clearPointsHistory()
        authViewModel.logout()
        activity?.finish()
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