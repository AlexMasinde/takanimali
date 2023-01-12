package com.example.takanimali.ui.report

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.takanimali.model.*
import com.example.takanimali.ui.auth.AuthViewModel
import com.example.takanimali.ui.reusablecomponents.*
import com.example.takanimali.ui.theme.Grey
import com.example.takanimali.ui.utils.blockList
import com.example.takanimali.ui.utils.locationList
import com.example.takanimali.ui.utils.wasteTypeList
import com.google.accompanist.systemuicontroller.rememberSystemUiController


@Composable
fun ReportScreenContent(
    navController: NavController,
    reportViewModel: ReportViewModel = viewModel(),
    authViewModel: AuthViewModel,
    modifier: Modifier = Modifier
) {

    val systemUiController = rememberSystemUiController()
    DisposableEffect(systemUiController) {
        systemUiController.setStatusBarColor(Grey)
        onDispose { }
    }

    val authenticatedUser by authViewModel.authenticatedUser.collectAsState()

    val accessToken = authenticatedUser.details?.access_token
    val userId = authenticatedUser.details?.id

    val selectedZone = reportViewModel.selectedZone
    val selectedLocation = reportViewModel.selectedLocation
    val selectedBlock = reportViewModel.selectedBlock
    val zoneList = reportViewModel.zoneList
    val selectedWasteType = reportViewModel.selectedWasteType
    val selectedWaste = reportViewModel.selectedWaste
    val wasteList = reportViewModel.wasteList


    fun updateZone(zoneListItem: ZoneListItem) {
        reportViewModel.updateZone(zoneListItem)
    }

    fun updateLocation(locationListItem: LocationListItem) {
        reportViewModel.updateLocation(locationListItem)
    }

    fun updateBlock(blockListItem: BlockListItem) {
        reportViewModel.updateBlock(blockListItem)
    }

    fun updateWasteType(wasteTypeListItem: WasteTypeListItem) {
        reportViewModel.updateWasteType(wasteTypeListItem)
    }

    fun updateWaste(wasteListItem: WasteListItem) {
        reportViewModel.updateWaste(wasteListItem)
    }

    Column {
        Box(modifier.padding(bottom = 24.dp)) {
            PageHeader("Report Waste", navController, "home")
        }
        Box(modifier.padding(horizontal = 24.dp)) {
            Text(text = "Waste Type", style = MaterialTheme.typography.h5)
        }
        Box(modifier.padding(horizontal = 24.dp, vertical = 8.dp)) {
            DropDownWasteType(
                selectedWasteType,
                wasteTypeList,
                updateWasteType = { updateWasteType(it) })
        }
        Box(modifier.padding(horizontal = 24.dp)) {
            Text(text = "Waste", style = MaterialTheme.typography.h5)
        }
        Box(modifier.padding(horizontal = 24.dp, vertical = 8.dp)) {

                DropDownWaste(
                    selectedWaste,
                    wasteList,
                    updateWaste = { updateWaste(it) },
                )

        }
        Box(modifier.padding(horizontal = 24.dp)) {
            Text(text = "Location", style = MaterialTheme.typography.h5)
        }
        Box(modifier.padding(horizontal = 24.dp, vertical = 8.dp)) {

                DropDownLocation(
                    selectedLocation,
                    locationList,
                    updateLocation = { updateLocation(it) })

        }
        Box(modifier.padding(horizontal = 24.dp)) {
            Text(text = "Zone", style = MaterialTheme.typography.h5)
        }
        Box(modifier.padding(horizontal = 24.dp, vertical = 8.dp)) {

                DropDownZone(
                    selectedZone,
                    zoneList,
                    updateZone = { updateZone(it) })

        }
        Box(modifier.padding(horizontal = 24.dp)) {
            Text(text = "Block", style = MaterialTheme.typography.h5)
        }
        Box(modifier.padding(horizontal = 24.dp, vertical = 8.dp)) {
                DropDownBlock(
                    selectedBlock,
                    blockList,
                    updateBlock = { updateBlock(it) })
        }

        Box(modifier.padding(horizontal = 24.dp, vertical = 12.dp)) {
            PrimaryButton("Report", false, onClick = { reportViewModel.reportWaste(accessToken) }, false)
        }
    }

}