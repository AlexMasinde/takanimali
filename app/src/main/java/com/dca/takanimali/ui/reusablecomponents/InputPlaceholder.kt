package com.example.takanimali.ui.reusablecomponents

import androidx.compose.foundation.layout.Box
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun InputPlaceholder(innerTextFiled: @Composable () -> Unit, placeholder: String, value: String) {
    val valueAvailable = value.isNotEmpty()
    val placeholderText = if (valueAvailable) "" else placeholder
    Box() {
        Text(text = placeholderText, style = MaterialTheme.typography.body2)
        innerTextFiled()
    }
}