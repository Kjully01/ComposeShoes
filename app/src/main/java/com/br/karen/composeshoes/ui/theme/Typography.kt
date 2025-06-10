package com.br.karen.composeshoes.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.br.karen.composeshoes.R

val PoppinsFontFamily = FontFamily(
    Font(R.font.poppins),
    Font(R.font.poppins_light),
    Font(R.font.poppins_medium),
    Font(R.font.poppins_semibold),
    Font(R.font.poppins_bold)
)

val AppTypography = Typography(
    displayMedium = TextStyle(
        fontFamily = PoppinsFontFamily,
        fontWeight = FontWeight.SemiBold,
        letterSpacing = 0.2.sp,
        fontSize = 40.sp
    ),
    headlineLarge = TextStyle(
        fontFamily = PoppinsFontFamily,
        fontWeight = FontWeight.Bold,
        letterSpacing = 0.2.sp,
        fontSize = 14.sp
    ),
    titleLarge = TextStyle(
        fontFamily = PoppinsFontFamily,
        fontWeight = FontWeight.SemiBold,
        letterSpacing = 0.2.sp,
        fontSize = 16.sp
    ),
    titleMedium = TextStyle(
        fontFamily = PoppinsFontFamily,
        fontWeight = FontWeight.Light,
        letterSpacing = 0.2.sp,
        fontSize = 20.sp
    ),
    titleSmall = TextStyle(
        fontFamily = PoppinsFontFamily,
        fontWeight = FontWeight.SemiBold,
        letterSpacing = 0.2.sp,
        fontSize = 10.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = PoppinsFontFamily,
        fontWeight = FontWeight.Normal,
        letterSpacing = 0.2.sp,
        fontSize = 12.sp
    ),
    labelLarge = TextStyle(
        fontFamily = PoppinsFontFamily,
        fontWeight = FontWeight.SemiBold,
        letterSpacing = 0.2.sp,
        fontSize = 18.sp
    ),
    labelMedium = TextStyle(
        fontFamily = PoppinsFontFamily,
        fontWeight = FontWeight.Normal,
        letterSpacing = 0.2.sp,
        fontSize = 10.sp
    ),
    labelSmall = TextStyle(
        fontFamily = PoppinsFontFamily,
        fontWeight = FontWeight.Medium,
        letterSpacing = 0.2.sp,
        fontSize = 10.sp
    )
)