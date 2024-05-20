package com.luanafernandes.weather_app_compose.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.luanafernandes.weather_app_compose.R

// Set of Material typography styles to start with
val ubuntuFont = FontFamily(listOf(
    Font(R.font.ubuntu)
))
val russoFont = FontFamily(listOf(
    Font(R.font.russo_one)
))

val questrialFont = FontFamily(listOf(
    Font(R.font.questrial_regular)
))

val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = questrialFont,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)