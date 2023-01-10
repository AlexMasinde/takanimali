package com.example.takanimali.ui.splash


import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.takanimali.R
import com.example.takanimali.ui.theme.Grey
import com.example.takanimali.ui.theme.TakaNiMaliTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(navController: NavController) {

    val systemUiController = rememberSystemUiController()

    DisposableEffect(systemUiController) {
        systemUiController.setStatusBarColor(Grey)
        onDispose { }
    }

    var startAnimation by remember {
        mutableStateOf(false)
    }

    var alphaAnimation = animateFloatAsState(
        targetValue = if(startAnimation) 1F else 0F,
        animationSpec = tween(
            durationMillis = 3000
        )
    )

    LaunchedEffect(key1 = true ) {
        startAnimation = true
        delay(4000)
        navController.popBackStack()
        navController.navigate(("home"))
    }


    SplashContent(alpha = alphaAnimation.value)
}


@Composable
fun SplashContent(alpha: Float) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Grey),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(R.drawable.logo),
            contentDescription = "logo",
            modifier = Modifier
                .size(120.dp)
                .alpha(alpha = alpha)
        )
    }
}


@Preview
@Composable
fun SplashScreenPreview() {
    TakaNiMaliTheme() {
        SplashContent(alpha = 1f )
    }
}
