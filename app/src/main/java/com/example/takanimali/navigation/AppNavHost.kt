package com.example.takanimali.navigation


import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.takanimali.ui.auth.AuthViewModel
import com.example.takanimali.ui.home.Home
import com.example.takanimali.ui.auth.Login
import com.example.takanimali.ui.changepassword.ChangePasswordScreen
import com.example.takanimali.ui.collect.CollectContent
import com.example.takanimali.ui.collect.CollectScreen
import com.example.takanimali.ui.collect.CollectViewModel
import com.example.takanimali.ui.collection.CollectionScreenContent
import com.example.takanimali.ui.help.Help
import com.example.takanimali.ui.home.HomeNavigationItem
import com.example.takanimali.ui.points.PointsScreenContent
import com.example.takanimali.ui.profile.ProfileDetails
import com.example.takanimali.ui.profile.ProfileScreenContents
import com.example.takanimali.ui.register.RegisterContent
import com.example.takanimali.ui.register.RegisterScreen
import com.example.takanimali.ui.register.RegisterViewModel
import com.example.takanimali.ui.report.ReportScreen
import com.example.takanimali.ui.report.ReportScreenContent
import com.example.takanimali.ui.report.ReportViewModel

@Composable
fun AppNavHost(
    authViewModel: AuthViewModel,
    registerViewModel: RegisterViewModel,
    reportViewModel: ReportViewModel,
    collectViewModel: CollectViewModel,
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
                Home(authViewModel,navController,
                    onNavigateToLoginScreen = {
                        navController.navigate("login")
                    })
            }
            composable(ROUTE_LOGIN) {
                Login(navController, authViewModel)
            }
            composable(ROUTE_HISTORY) {
                CollectionScreenContent(navController)
            }
            composable(ROUTE_POINTS) {
                PointsScreenContent(navController)
            }
            composable(ROUTE_PROFILE) {
                ProfileScreenContents(navController, authViewModel)
            }
            composable(ROUTE_CHANGE_PASSWORD) {
                ChangePasswordScreen(navController)
            }
            composable(ROUTE_PROFILE_DETAILS) {
                ProfileDetails(navController)
            }
            composable(ROUTE_HELP) {
                Help(navController)
            }
            composable(ROUTE_REPORT) {
                ReportScreen(navController, reportViewModel, authViewModel)
            }
            composable(ROUTE_REGISTER){
                RegisterScreen(navController, registerViewModel)
            }
            composable(ROUTE_COLLECT) {
                CollectScreen(navController, collectViewModel, authViewModel)
            }

        }
    }
}