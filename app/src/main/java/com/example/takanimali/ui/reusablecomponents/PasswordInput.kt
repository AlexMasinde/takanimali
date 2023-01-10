package com.example.takanimali.ui.reusablecomponents

import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.takanimali.R
import com.example.takanimali.ui.theme.BorderColor

@Composable
fun PasswordInput(value: String, placeholder: String, onUserValue: (String) -> Unit, label: String) {
    val passwordAvailable = value.isNotEmpty()
    Column(modifier = Modifier.padding(horizontal = 21.dp, vertical = 8.dp)) {
        Text(
            text = label,
            style = MaterialTheme.typography.body2,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        OutlinedTextField(
            value = value,
            onValueChange = onUserValue,
            visualTransformation = if (passwordAvailable) PasswordVisualTransformation() else VisualTransformation.None,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                unfocusedBorderColor = BorderColor,
                focusedBorderColor = BorderColor,
                cursorColor = BorderColor
            ),
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            textStyle = MaterialTheme.typography.subtitle2,
            placeholder = { Text(text = placeholder, style = MaterialTheme.typography.body2) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )

    }
}