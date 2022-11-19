package com.example.takanimali.ui.reusablecomponents

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.takanimali.ui.theme.Primary
import com.example.takanimali.ui.theme.White

@Composable
fun PrimaryButton(buttonText: String, disabled: Boolean, onClick: () -> Unit) {
    Box(modifier = Modifier.padding(horizontal = 21.dp)) {
        Button(
            onClick = { onClick() },
            enabled = !disabled,
            modifier = Modifier
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Primary,
                disabledBackgroundColor = Primary
            )
        ) {
            Text(
                text = buttonText,
                style = MaterialTheme.typography.button,
                modifier = Modifier.padding(vertical = 6.dp)
            )
        }
    }
}