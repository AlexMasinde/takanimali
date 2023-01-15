package com.dca.takanimali.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.dca.takanimali.R

val Inter = FontFamily(
    Font(R.font.inter_regular, FontWeight.Normal),
    Font(R.font.inter_medium, FontWeight.Medium),
    Font(R.font.inter_semi_bold, FontWeight.SemiBold)

)

val Typography = Typography(
    h1 = TextStyle(
        fontFamily = Inter,
        fontWeight = FontWeight.SemiBold,
        fontSize = 28.sp,
        color = HeadingTextColor
    ),
    h2 = TextStyle(
        fontFamily = Inter,
        fontWeight = FontWeight.SemiBold,
        fontSize = 24.sp,
        color = HeadingTextColor
    ),
    h3 = TextStyle(
        fontFamily = Inter,
        fontWeight = FontWeight.SemiBold,
        fontSize = 18.sp,
        color = HeadingTextColor
    ),
    h4 = TextStyle(
        fontFamily = Inter,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
        color = HeadingTextColor
    ),
    h5 = TextStyle(
        fontFamily = Inter,
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp,
        color = HeadingTextColor
    ),
    h6 = TextStyle(
        fontFamily = Inter,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        color = ProfileHeadingColor
    ),
    subtitle1 = TextStyle(
        fontFamily = Inter,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        color = SecondaryTextColor
    ),
    subtitle2 = TextStyle(
        fontFamily = Inter,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        color = SecondaryTextColor
    ),
    body1 = TextStyle(
        fontFamily = Inter,
        fontWeight = FontWeight.Medium,
        color = HeadingTextColor,
        fontSize = 14.sp
    ),
    body2 = TextStyle(
        fontFamily = Inter,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        color = SecondaryTextColor
    ),
    button = TextStyle(
        fontFamily = Inter,
        fontWeight = FontWeight.Medium,
        color = Grey,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = Inter,
        fontWeight = FontWeight.Medium,
        color = HeadingTextColor,
        fontSize = 16.sp
    ),
    overline = TextStyle(
        fontFamily = Inter,
        fontWeight = FontWeight.Medium,
        color = HeadingTextColor,
        fontSize = 18.sp
    )
)