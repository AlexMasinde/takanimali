package com.example.takanimali.ui.reusablecomponents

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import com.example.takanimali.ui.theme.Primary
import com.example.takanimali.ui.theme.SecondaryTextColor

@Composable
fun SpannableText(mainText: String, spanText: String) {
    val annotatedString = buildAnnotatedString {
        append(mainText)
        withStyle(style = SpanStyle(Primary)) {
            append(spanText)
        }
    }
    Text(text = annotatedString, style = MaterialTheme.typography.h6, color = SecondaryTextColor )
}