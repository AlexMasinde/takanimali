package com.example.takanimali.ui.collect

import android.util.Log
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.takanimali.model.*
import com.example.takanimali.ui.auth.AuthViewModel
import com.example.takanimali.ui.report.components.DropDownWrapper
import com.example.takanimali.ui.reusablecomponents.*
import com.example.takanimali.ui.theme.Grey
import com.example.takanimali.ui.utils.blockList
import com.example.takanimali.ui.utils.locationList
import com.example.takanimali.ui.utils.wasteTypeList
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun CollectContent(
    navController: NavController,
    collectViewModel: CollectViewModel = viewModel(),
    authViewModel: AuthViewModel = hiltViewModel()
) {
    val collectFormState by collectViewModel.collectFormState
    val collectUiState by collectViewModel.collectUiState.collectAsState()
    val authenticatedUser by authViewModel.authenticatedUser.collectAsState()

    val idError = collectUiState.idError
    val quantityError = collectUiState.quantityError
    val internetAccessError = collectUiState.IOAuthError
    val httpError = collectUiState.HTTPAuthError

    val systemUiController = rememberSystemUiController()
    DisposableEffect(systemUiController) {
        systemUiController.setStatusBarColor(Grey)
        onDispose { }
    }

    val selectedZone = collectViewModel.selectedZone
    val selectedLocation = collectViewModel.selectedLocation
    val selectedBlock = collectViewModel.selectedBlock
    val zoneList = collectViewModel.zoneList
    val selectedWasteType = collectViewModel.selectedWasteType
    val selectedWaste = collectViewModel.selectedWaste
    val wasteList = collectViewModel.wasteList

    val accessToken = authenticatedUser.details?.access_token
    val userId = authenticatedUser.details?.id

    val modifier: Modifier = Modifier


    fun updateZone(zoneListItem: ZoneListItem) {
        collectViewModel.updateZone(zoneListItem)
    }

    fun updateLocation(locationListItem: LocationListItem) {
        collectViewModel.updateLocation(locationListItem)
        Log.d("register Location", locationListItem.name)
    }

    fun updateBlock(blockListItem: BlockListItem) {
        collectViewModel.updateBlock(blockListItem)
        Log.d("register Block", blockListItem.name)
    }

    fun updateWasteType(wasteTypeListItem: WasteTypeListItem) {
        collectViewModel.updateWasteType(wasteTypeListItem)
        Log.d("register Waste Type", wasteTypeListItem.name)
    }

    fun updateWaste(wasteListItem: WasteListItem) {
        collectViewModel.updateWaste(wasteListItem)
        Log.d("register Waste", wasteListItem.name)
    }

    Column(modifier.verticalScroll(enabled = true, state = ScrollState(0))) {
        Box {
            PageHeader(text = "Collect", navController, "home")
        }
        Input(
            value = collectFormState.userId,
            placeholder = "Member Id",
            onUserValue = { collectViewModel.onUserIdChange(it) },
            label = "Member Id",
            type = "Text"
        )

        if (idError.isNotEmpty()) {
            Box(modifier.padding(bottom = 10.dp, start = 10.dp)) {
                ErrorText(error = idError)
            }
        }
        Box(modifier.padding(horizontal = 24.dp)) {
            Text(text = "Waste Type", style = MaterialTheme.typography.body2)
        }
        Box(modifier.padding(horizontal = 24.dp, vertical = 12.dp)) {
            DropDownWasteType(
                selectedWasteType,
                wasteTypeList,
                updateWasteType = { updateWasteType(it) })
        }
        Box(modifier.padding(horizontal = 24.dp)) {
            Text(text = "Waste", style = MaterialTheme.typography.body2)
        }
        Box(modifier.padding(horizontal = 24.dp, vertical = 12.dp)) {
            DropDownWrapper(
                DropDownWaste(
                    selectedWaste,
                    wasteList,
                    updateWaste = { updateWaste(it) },
                )
            )
        }
        Box(modifier.padding(horizontal = 24.dp)) {
            Text(text = "Location", style = MaterialTheme.typography.body2)
        }
        Box(modifier.padding(horizontal = 24.dp, vertical = 12.dp)) {
            DropDownWrapper(
                DropDownLocation(
                    selectedLocation,
                    locationList,
                    updateLocation = { updateLocation(it) })
            )
        }
        Box(modifier.padding(horizontal = 24.dp)) {
            Text(text = "Zone", style = MaterialTheme.typography.body2)
        }
        Box(modifier.padding(horizontal = 24.dp, vertical = 12.dp)) {
            DropDownWrapper(
                DropDownZone(
                    selectedZone,
                    zoneList,
                    updateZone = { updateZone(it) })
            )
        }
        Box(modifier.padding(horizontal = 24.dp)) {
            Text(text = "Block", style = MaterialTheme.typography.body2)
        }
        Box(modifier.padding(horizontal = 24.dp, vertical = 12.dp)) {
            DropDownWrapper(
                DropDownBlock(
                    selectedBlock,
                    blockList,
                    updateBlock = { updateBlock(it) })
            )
        }
        Input(
            value = collectFormState.quantity,
            placeholder = "Quantity",
            onUserValue = { collectViewModel.onQuantityChange(it) },
            label = "Quantity",
            type = "Number"
        )
        if (quantityError.isNotEmpty()) {
            Box(modifier.padding(start = 10.dp)) {
                ErrorText(error = quantityError)
            }
        }
        Box(modifier.padding(horizontal = 24.dp, vertical = 12.dp)) {
            PrimaryButton(
                "Collect Waste",
                false,
                onClick = { collectViewModel.collectWaste(userId, accessToken) },
                false
            )
        }
        if (internetAccessError.isNotEmpty()) {
            Box(modifier.padding(start = 10.dp)) {
                ErrorText(error = internetAccessError)
            }
        }
        if (httpError.isNotEmpty()) {
            Box(modifier.padding(start = 10.dp, bottom = 14.dp)) {
                ErrorText(error = httpError)
            }
        }
    }

}