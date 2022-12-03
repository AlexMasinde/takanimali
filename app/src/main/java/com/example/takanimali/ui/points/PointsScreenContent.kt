package com.example.takanimali.ui.points

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.takanimali.R
import com.example.takanimali.ui.points.components.PointsScreenTop
import com.example.takanimali.ui.reusablecomponents.PageHeader
import com.example.takanimali.ui.reusablecomponents.PrimaryButton
import com.example.takanimali.ui.reusablecomponents.SpannableText
import com.example.takanimali.ui.theme.Grey
import com.example.takanimali.ui.theme.TakaNiMaliTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController


@Composable
fun PointsScreenContent(navController: NavController, modifier: Modifier = Modifier) {

    val systemUiController = rememberSystemUiController()
    DisposableEffect(systemUiController) {
        systemUiController.setStatusBarColor(Grey)
        onDispose { }
    }

    Column(modifier.fillMaxWidth()) {
        PageHeader(text = "Taka Points", navController)
        PointsScreenTop()
        Box(
            modifier
                .padding(start = 24.dp, top = 0.dp, end = 24.dp, bottom = 24.dp)
                .fillMaxWidth()
                .wrapContentWidth(align = Alignment.CenterHorizontally)
        ) {
            SpannableText(mainText = stringResource(R.string.redeem_points_text), spanText = " Kshs. 300")
        }
        Box(modifier.padding(horizontal = 24.dp)) {
            PrimaryButton(
                buttonText = "Redeem",
                disabled = false,
                onClick = { /*TODO*/ },
                loadingState = false
            )
        }
    }
}