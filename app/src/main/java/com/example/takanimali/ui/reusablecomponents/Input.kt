package com.example.takanimali.ui.reusablecomponents

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.takanimali.ui.theme.BorderColor


@Composable
fun Input(
    value: String,
    placeholder: String,
    onUserValue: (String) -> Unit,
    label: String,
    type: String
) {

    val keyboardType = when(type) {
        "Email" -> KeyboardOptions(keyboardType = KeyboardType.Email)
        "Phone" -> KeyboardOptions(keyboardType = KeyboardType.Phone)
        "Number" -> KeyboardOptions(keyboardType = KeyboardType.Number)
        else -> KeyboardOptions(keyboardType = KeyboardType.Text)
    }

    Column(modifier = Modifier.padding(horizontal = 21.dp, vertical = 8.dp)) {
        Text(
            text = label,
            style = MaterialTheme.typography.h5,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        OutlinedTextField(
            value = value,
            onValueChange = onUserValue,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                unfocusedBorderColor = BorderColor,
                focusedBorderColor = BorderColor,
                cursorColor = BorderColor
            ),
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            textStyle = MaterialTheme.typography.subtitle2,
            placeholder = { Text(text = placeholder, style = MaterialTheme.typography.subtitle2) },
            keyboardOptions = keyboardType
        )

    }
}