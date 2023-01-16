package com.dca.takanimali.navigation


import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.dca.takanimali.ui.auth.Login
import com.dca.takanimali.ui.collect.CollectScreen
import com.dca.takanimali.ui.collection.CollectionScreen
import com.dca.takanimali.ui.help.Help
import com.dca.takanimali.ui.home.Home
import com.dca.takanimali.ui.points.PointsScreen
import com.dca.takanimali.ui.profile.ProfileDetails
import com.dca.takanimali.ui.profile.ProfileScreenContents
import com.dca.takanimali.ui.register.RegisterScreen
import com.dca.takanimali.ui.report.ReportScreen
import com.dca.takanimali.ui.splash.SplashScreen
import com.dca.takanimali.ui.terms.Terms
import com.dca.takanimali.ui.verify.VerifyScreen

@RequiresApi(Build.VERSION_CODES.O)
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