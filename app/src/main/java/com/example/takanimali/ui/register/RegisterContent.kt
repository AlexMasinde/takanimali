package com.example.takanimali.ui.register

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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.takanimali.model.BlockListItem
import com.example.takanimali.model.LocationListItem
import com.example.takanimali.model.ZoneListItem
import com.example.takanimali.ui.report.components.DropDownWrapper
import com.example.takanimali.ui.reusablecomponents.*
import com.example.takanimali.ui.theme.Grey
import com.example.takanimali.ui.utils.blockList
import com.example.takanimali.ui.utils.locationList
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun RegisterContent(
    navController: NavController,
    registerViewModel: RegisterViewModel = viewModel(),
    modifier: Modifier = Modifier
) {

    val systemUiController = rememberSystemUiController()

    DisposableEffect(systemUiController) {
        systemUiController.setStatusBarColor(Grey)
        onDispose {  }
    }

    val registerFormState by registerViewModel.registerFormState
    val registerUiState by registerViewModel.registerUiState.collectAsState()

    val emailError = registerUiState.emailError
    val nameError = registerUiState.nameError
    val passwordError = registerUiState.passwordError
    val phoneNumberError = registerUiState.phoneNumberError

    val selectedZone = registerViewModel.selectedZone
    val selectedLocation = registerViewModel.selectedLocation
    val selectedBlock = registerViewModel.selectedBlock
    val zoneList = registerViewModel.zoneList
    val httpAuthError = registerUiState.HTTPAuthError
    val iOAuthError = registerUiState.IOAuthError

    val buttonDisabled = registerUiState.registerUiError

    fun updateZone(zoneListItem: ZoneListItem) {
        registerViewModel.updateZone(zoneListItem)
    }

    fun updateLocation(locationListItem: LocationListItem) {
        registerViewModel.updateLocation(locationListItem)
        Log.d("register Location", locationListItem.name)
    }

    fun updateBlock(blockListItem: BlockListItem) {
        registerViewModel.updateBlock(blockListItem)
        Log.d("register Block", blockListItem.name)
    }


    Column(modifier.verticalScroll(enabled = true, state = ScrollState(0)).padding(bottom = 24.dp)) {
        Box {
            PageHeader("Register", navController)
        }
//        Column(
//            modifier
//                .padding(vertical = 24.dp)
//                .fillMaxWidth(),
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            Text(text = "Join Taka ni Mali", style = MaterialTheme.typography.h2)
//            Divider(thickness = 8.dp, color = Grey)
//            Text(text = "Fill in your details below", style = MaterialTheme.typography.subtitle1)
//        }
        Input(
            value = registerFormState.email,
            placeholder = "Email",
            onUserValue = { registerViewModel.onEmailChange(it) },
            label = "Email Address",
            type = "Email"
        )
        emailError?.let {
            ErrorText(error = emailError)
        }
        Input(
            value = registerFormState.name,
            placeholder = "Name",
            onUserValue = { registerViewModel.onNameChange(it) },
            label = "Your Name",
            type = "Text"
        )
        nameError?.let {
            ErrorText(error = nameError)
        }
        Input(
            value = registerFormState.phone_number,
            placeholder = "Phone",
            onUserValue = { registerViewModel.onPhoneChange(it) },
            label = "Phone Number",
            type = "Phone"
        )
        phoneNumberError?.let {
            ErrorText(error = phoneNumberError)
        }
        PasswordInput(
            value = registerFormState.password,
            placeholder = "Password",
            onUserValue = { registerViewModel.onPasswordChange(it) },
            label = "Password"
        )
        passwordError?.let {
            ErrorText(error = passwordError)
        }
        Box(modifier.padding(horizontal = 24.dp)) {
            Text(text = "Location", style = MaterialTheme.typography.h5)
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
            Text(text = "Zone", style = MaterialTheme.typography.h5)
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
            Text(text = "Block", style = MaterialTheme.typography.h5)
        }
        Box(modifier.padding(horizontal = 24.dp, vertical = 12.dp)) {
            DropDownWrapper(
                DropDownBlock(
                    selectedBlock,
                    blockList,
                    updateBlock = { updateBlock(it) })
            )
        }
        Box(modifier.padding(horizontal = 24.dp, vertical = 12.dp)) {
            PrimaryButton("Register", buttonDisabled, onClick = { registerViewModel.register() }, false)
        }
        Box(modifier = Modifier.padding(top = 10.dp)) {
            iOAuthError?.let {
                ErrorText(error = iOAuthError)
            }
            httpAuthError?.let {
                ErrorText(error = httpAuthError)
            }
        }

    }

}