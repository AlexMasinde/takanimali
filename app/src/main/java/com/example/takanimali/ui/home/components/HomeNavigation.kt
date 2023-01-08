package com.example.takanimali.ui.home.components

import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.takanimali.ui.home.HomeNavigationItem
import com.example.takanimali.ui.theme.ProfileBottomBorder

@Composable
fun HomeNavigation(navController: NavController, roleId: Int?, modifier: Modifier = Modifier) {
    val isNotTeamLeader = roleId != 1
    Column(modifier.padding(start = 24.dp, end = 24.dp, bottom = 16.dp)) {
        Column(
            modifier
                .fillMaxWidth(),
        ) {
            if (!isNotTeamLeader) {
                Text(
                    text = "Team Leader",
                    style = MaterialTheme.typography.subtitle1,
                    modifier = modifier.padding(bottom = 16.dp)
                )

                Box(modifier.padding(bottom = 16.dp)) {
                    NavigationCard(HomeNavigationItem.Collect, navController)
                }
                Divider(thickness = 1.dp, color = ProfileBottomBorder)
            }
        }
        Row(
            modifier
                .padding(top = 16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            NavigationCard(HomeNavigationItem.Report, navController)
            NavigationCard(HomeNavigationItem.Redeem, navController)
        }
        Row(
            modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(modifier.padding(top = 16.dp)) {
                NavigationCard(HomeNavigationItem.History, navController)
            }
            Box(modifier.padding(top = 16.dp)) {
                NavigationCard(HomeNavigationItem.Profile, navController)
            }
        }
//        Row(
//            modifier
//                .fillMaxWidth(),
//            horizontalArrangement = Arrangement.SpaceBetween
//        ) {
//            Box(modifier.padding(top = 16.dp)) {
//                NavigationCard(HomeNavigationItem.Password, navController)
//            }
//            Box (modifier.padding(top= 16.dp)){
//                NavigationCard(HomeNavigationItem.Logout, navController)
//            }
//        }
    }

}