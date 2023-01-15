package com.dca.takanimali.navigation

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.dca.takanimali.ui.theme.*

@Composable
fun AppBottomNavigation(navController: NavController) {
    val navItems = listOf(
        BottomNavItem.Home,
        BottomNavItem.Collection,
        BottomNavItem.Points,
        BottomNavItem.Profile
    )

    BottomNavigation(backgroundColor = Grey, elevation = 0.dp) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route


        navItems.forEach { item ->
            val selected = currentRoute == item.navRoute
            BottomNavigationItem(
                icon = {
                    Icon(
                        painterResource(id = if (selected) item.icon else item.selectedIcon),
                        contentDescription = stringResource(item.title)
                    )
                },
                label = {
                    Text(
                        text = stringResource(item.title),
                        style = MaterialTheme.typography.subtitle2,
                        color = if (selected) Primary else SecondaryTextColor
                    )
                },
                selectedContentColor = Primary,
                unselectedContentColor = SecondaryTextColor,
                alwaysShowLabel = true,
                selected = currentRoute == item.navRoute,
                onClick = {
                    navController.navigate(item.navRoute) {
                        navController.graph.startDestinationRoute?.let { screen_route ->
                            popUpTo(screen_route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }

}