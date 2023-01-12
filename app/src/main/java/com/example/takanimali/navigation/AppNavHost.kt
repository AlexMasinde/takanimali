package com.example.takanimali.navigation


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
import com.example.takanimali.ui.splash.SplashScreen
import com.example.takanimali.ui.terms.Terms
import com.example.takanimali.ui.verify.VerifyScreen

@Composable
fun AppNavHost(

    navController: NavHostController = rememberNavController(),
    startDestination: String = ROUTE_HOME
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val showBottomNavigationBar = when (navBackStackEntry?.destination?.route) {
        "login" -> false
        "register" -> false
        else -> true
    }
    NavHost(navController = navController, startDestination = startDestination) {
        composable(ROUTE_SPLASH) {
            SplashScreen(navController)
        }
        composable(ROUTE_HOME) {
            Home(navController)
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
        composable(ROUTE_REGISTER) {
            RegisterScreen(navController)
        }
        composable(ROUTE_COLLECT) {
            CollectScreen(navController)
        }
        composable(ROUTE_TERMS) {
            Terms(navController)
        }
        composable(ROUTE_VERIFY) {
            VerifyScreen(navController)
        }
    }

}