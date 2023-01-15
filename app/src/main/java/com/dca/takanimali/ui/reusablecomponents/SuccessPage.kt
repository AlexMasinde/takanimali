package com.dca.takanimali.ui.reusablecomponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.dca.takanimali.ui.collect.CollectViewModel
import com.dca.takanimali.ui.register.RegisterViewModel
import com.dca.takanimali.ui.report.ReportViewModel
import com.dca.takanimali.R

@Composable
fun SuccessPage(
    navController: NavController,
    destination: String,
    message: String,
    buttonText: String? = null,
    reportViewModel: ReportViewModel = hiltViewModel(),
    collectViewModel: CollectViewModel = hiltViewModel(),
    registerViewModel: RegisterViewModel = hiltViewModel(),
) {
    val successImage = painterResource(id = R.drawable.sucess_icon)

    val modifier: Modifier = Modifier

    fun updateState() {
        when (destination) {
            "report" -> {
                reportViewModel.updateReportState()
            }
            "collect" -> {
                collectViewModel.updateCollectState()
            }
            else -> {
                registerViewModel.updateRegisterUiState()
                navController.navigate(destination)
            }
        }
    }

    Column(
        modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(painter = successImage, contentDescription = "Success")
        Box(modifier.padding(top = 8.dp)) {
            Text(text = message, style = MaterialTheme.typography.subtitle2)
        }
        Box(modifier.padding(16.dp)) {
            PrimaryButton(
                buttonText =  buttonText ?: "Back",
                disabled = false,
                onClick = { updateState() },
                loadingState = false
            )
        }
    }
}