package com.dca.takanimali.ui.reusablecomponents

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import com.dca.takanimali.ui.theme.Primary

@Composable
fun PrimaryButton(buttonText: String, disabled: Boolean, onClick: () -> Unit, loadingState: Boolean) {
    val alpha = if(loadingState) 0.75F else 1F
    Box(modifier = Modifier.padding(horizontal = 0.dp).alpha(alpha = alpha)) {
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
                text = if(loadingState) "Loading..." else buttonText,
                style = MaterialTheme.typography.button,
                modifier = Modifier.padding(vertical = 6.dp)
            )
        }
    }
}