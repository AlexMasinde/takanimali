package com.example.takanimali.navigation


import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.takanimali.ui.auth.Login
import com.example.takanimali.ui.collect.CollectScreen
import com.example.takanimali.ui.collection.CollectionScreen
import com.example.takanimali.ui.help.Help
import com.example.takanimali.ui.home.Home
import com.example.takanimali.ui.points.PointsScreen
import com.example.takanimali.ui.profile.ProfileDetails
import com.example.takanimali.ui.profile.ProfileScreenContents
import com.example.takanimali.ui.register.RegisterScreen
import com.example.takanimali.ui.report.ReportScreen
import com.example.takanimali.ui.terms.Terms

@Composable
fun AppNavHost (

    navController: NavHostController = rememberNavController(),
    startDestination: String = ROUTE_HOME
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val showBottomNavigationBar = when (navBackStackEntry?.destination?.route) {
        "login" -> false
        "register" -> false
        else -> true
    }

    Scaffold()
    {
        NavHost(navController = navController, startDestination = startDestination) {
            composable(ROUTE_HOME) {
                Home(navController,
                    onNavigateToLoginScreen = {
                        navController.navigate("login")
                    })
            }
            composable(ROUTE_LOGIN) {
                Login(navController)
            }
            composable(ROUTE_HISTORY) {
                CollectionScreen(navController)
            }
            composable(ROUTE_POINTS) {
                PointsScreen(navController)
            }
            composable(ROUTE_PROFILE) {
                ProfileScreenContents(navController)
            }

            composable(ROUTE_PROFILE_DETAILS) {
                ProfileDetails(navController)
            }
            composable(ROUTE_HELP) {
                Help(navController)
            }
            composable(ROUTE_REPORT) {
                ReportScreen(navController)
            }
            composable(ROUTE_REGISTER){
                RegisterScreen(navController)
            }
            composable(ROUTE_COLLECT) {
                CollectScreen(navController)
            }
            composable(ROUTE_TERMS) {
                Terms(navController)
            }

        }
    }
}