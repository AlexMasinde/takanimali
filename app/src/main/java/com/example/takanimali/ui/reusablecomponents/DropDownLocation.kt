package com.example.takanimali.ui.reusablecomponents

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import com.example.takanimali.model.LocationListItem
import com.example.takanimali.ui.theme.BorderColor

@Composable
fun DropDownLocation(
    selectedLocation: LocationListItem,
    locationList: List<LocationListItem>,
    updateLocation: (LocationListItem) -> Unit,
    modifier: Modifier = Modifier
) {
    var textFieldSize by remember { mutableStateOf(Size.Zero) }
    var expanded by remember { mutableStateOf(false) }

    val icon = if (expanded) {
        Icons.Filled.KeyboardArrowUp
    } else {
        Icons.Filled.KeyboardArrowDown
    }

    fun updateAndExpand(locationListItem: LocationListItem) {
        updateLocation(locationListItem)
        expanded = false
    }

    OutlinedTextField(
        value = selectedLocation.name, onValueChange = { TODO() },
        readOnly = true,
        modifier = modifier
            .fillMaxWidth()
            .onGloballyPositioned { coordinates ->
                textFieldSize = coordinates.size.toSize()
            },
        trailingIcon = { Icon(icon, "Arrow", modifier.clickable { expanded = !expanded }) },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            unfocusedBorderColor = BorderColor,
            focusedBorderColor = BorderColor,
            cursorColor = BorderColor
        ),
        textStyle = MaterialTheme.typography.body2,
        shape = RoundedCornerShape(10.dp),
    )
    DropdownMenu(
        expanded = expanded, onDismissRequest = { expanded = false }, modifier.width(with(
            LocalDensity.current
        ) { textFieldSize.width.toDp() })
    ) {
        locationList.forEach { locationListItem ->
            DropdownMenuItem(onClick = { updateAndExpand(locationListItem) }) {
                Text(text = locationListItem.name, style = MaterialTheme.typography.subtitle2)
            }
        }
    }
}