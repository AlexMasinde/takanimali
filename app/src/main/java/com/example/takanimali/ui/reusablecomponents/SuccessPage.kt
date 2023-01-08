package com.example.takanimali.ui.reusablecomponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.takanimali.R
import com.example.takanimali.ui.collect.CollectViewModel
import com.example.takanimali.ui.report.ReportViewModel
import com.example.takanimali.ui.reusablecomponents.PrimaryButton
import com.example.takanimali.ui.theme.TakaNiMaliTheme

@Composable
fun SuccessPage(
    navController: NavController,
    destination: String,
    message: String,
    modifier: Modifier = Modifier,
    reportViewModel: ReportViewModel = hiltViewModel(),
    collectViewModel: CollectViewModel = hiltViewModel()
) {
    val successImage = painterResource(id = R.drawable.sucess_icon)


    fun updateState() {
        when (destination) {
            "report" -> {
                reportViewModel.updateReportState()
            }
            "collect" -> {
                collectViewModel.updateCollectState()
            }
            else -> {
                navController.navigate(destination)
            }
        }
    }


    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painter = successImage, contentDescription = "Success")
        Box(modifier.padding(top = 8.dp)) {
            Text(text = message, style = MaterialTheme.typography.subtitle2)
        }
        Box(modifier.padding(16.dp)) {
            PrimaryButton(
                buttonText = "Back",
                disabled = false,
                onClick = { updateState() },
                loadingState = false
            )
        }
    }
}