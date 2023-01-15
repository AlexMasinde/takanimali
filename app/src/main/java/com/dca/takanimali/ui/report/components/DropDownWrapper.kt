package com.dca.takanimali.ui.report.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DropDownWrapper(dropDown: Unit, modifier: Modifier = Modifier) {
    Box(modifier.padding(horizontal = 24.dp, vertical = 12.dp)) {
        dropDown
    }
}